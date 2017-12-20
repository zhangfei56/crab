package cn.nicky.crab.util;
        /*
* Copyright 2017 Alibaba Group
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.annotation.ThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

@ThreadSafe
public final class SyncCourierApiClient extends BaseApiClient {
    public final static String GROUP_HOST = "jisukdcx.market.alicloudapi.com";
    private SyncCourierApiClient(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<Builder, SyncCourierApiClient>{

        @Override
        protected SyncCourierApiClient build(BuilderParams params) {
            return new SyncCourierApiClient(params);
        }
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public static SyncCourierApiClient getInstance(){
        return getApiClassInstance(SyncCourierApiClient.class);
    }

    public ApiResponse getCourier(String type, String number) {
        String _apiPath = "/express/query";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.GET, GROUP_HOST, _apiPath);

        _apiRequest.addParam("type", type, ParamPosition.QUERY, true);
        _apiRequest.addParam("number", number, ParamPosition.QUERY, true);

        return syncInvoke(_apiRequest);
    }

    public ApiResponse getCourierCompany() {
        String _apiPath = "/express/type";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.GET, GROUP_HOST, _apiPath);


        return syncInvoke(_apiRequest);
    }

}

