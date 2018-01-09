package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.CourierCompany;
import cn.nicky.crab.model.po.OrderForm;
import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.repository.CourierCompanyRepository;
import cn.nicky.crab.repository.OrderRepository;
import cn.nicky.crab.repository.VoucherRepository;
import cn.nicky.crab.service.IOrderService;
import cn.nicky.crab.util.SyncCourierApiClient;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhang on 2017/11/13.
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private CourierCompanyRepository courierCompanyRepository;

    private SyncCourierApiClient courierApiClient;

    public OrderService(){
        courierApiClient = SyncCourierApiClient.newBuilder()
                .appKey("24728420")
                .appSecret("a23e82df26977b81ecfc46635a10b229")
                .build();
    }

    public ApiResponse getCourierCompany() {
        ApiResponse response = courierApiClient.getCourierCompany();

        return response;
    }

    public ApiResponse getCourier(String type, String number) {
        ApiResponse response = courierApiClient.getCourier(type, number);

        return response;
    }

    public void addOrder(OrderForm orderForm){
        voucherRepository.updateStatus(1, orderForm.getVoucher().getIdentityCode());
        Voucher voucher = voucherRepository.findByIdentityCode(orderForm.getVoucher().getIdentityCode());
        orderForm.setVoucher(voucher);
        orderForm.setCreateDateTime(new Date());
        orderRepository.save(orderForm);
    }

    public Page<OrderForm> findOrders(int userId, int status, Pageable pageable){
        Sort sort = new Sort(Sort.Direction.DESC, "createDateTime");
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<OrderForm> orderFormPage= null;
        if(status ==-1){
            orderFormPage = orderRepository.findByVoucher_UserId(userId, pageRequest);
        }else {
            orderFormPage = orderRepository.findByVoucher_UserIdAndVoucher_status(userId, status, pageRequest);
        }
        return orderFormPage;
    }

    public void addCourierCompany(List<CourierCompany> companies){
        courierCompanyRepository.save(companies);
    }

    public List<CourierCompany> findCourierCompany(boolean common){
        if(common){
            return courierCompanyRepository.findByCommon(common);
        }
        return courierCompanyRepository.findAll();
    }

    public void changeCourierCompanyStatus(int companyId){
        CourierCompany courierCompany = courierCompanyRepository.findOne(companyId);
        courierCompany.setCommon(!courierCompany.isCommon());
        courierCompanyRepository.save(courierCompany);
    }

    public void deliveryGoods(String identityCode, String type, String trackingNumber){
        Voucher voucher = voucherRepository.findByIdentityCode(identityCode);
        OrderForm orderForm = voucher.getOrderForm();
        orderForm.setTrackingNumber(trackingNumber);
        orderForm.setCourierCompanyType(type);
        orderRepository.save(orderForm);
        voucherRepository.updateStatus(2, identityCode);
    }

    public void finishOrder(String identityCode){
        voucherRepository.updateStatus(3, identityCode);
    }
}
