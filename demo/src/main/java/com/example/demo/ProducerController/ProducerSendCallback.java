package com.example.demo.ProducerController;

import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.github.thierrysquirrel.core.producer.DefaultSendCallback;
import org.springframework.stereotype.Component;

/**
 * Created by wangyunfu on 2020/3/24.
 */
@Component
public class ProducerSendCallback implements SendCallback {

    public void onSuccess(SendResult sendResult) {
        System.out.println("发送消息回调成功。。。。。。。。。。。");
    }

    @Override
    public void onException(OnExceptionContext onExceptionContext) {

    }
}
