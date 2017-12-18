package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.OrderForm;
import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.repository.OrderRepository;
import cn.nicky.crab.repository.VoucherRepository;
import cn.nicky.crab.service.IOrderService;
import cn.nicky.crab.util.SyncCourierApiClient;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhang on 2017/11/13.
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VoucherRepository voucherRepository;

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

    public Page<OrderForm> findOrders(int userId, Pageable pageable){
        return orderRepository.findByVoucher_UserId(userId, pageable);
    }
}
