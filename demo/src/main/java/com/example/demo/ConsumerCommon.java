package com.example.demo;

import com.aliyun.openservices.ons.api.PropertyValueConst;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.MessageListener;
import com.github.thierrysquirrel.annotation.RocketListener;
import com.github.thierrysquirrel.annotation.RocketMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangyunfu on 2020/3/23.
 */
@RocketListener(groupID = "GID_message",messageModel = PropertyValueConst.CLUSTERING)
public class ConsumerCommon {


    @MessageListener(topic = "commonB",tag = "*")
    public void delayed(String message) {
        System.out.println(message + "**************************");
        System.out.println("message");
    }
}
