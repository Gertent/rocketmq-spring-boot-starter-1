package com.example.demo;

import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.core.producer.MessageSendType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangyunfu on 2020/3/23.
 */
@RestController
@RocketMessage(groupID = "GID_common")
public class ProducerCommon {

    @GetMapping("/commonA")
    @CommonMessage(topic = "commonA", tag = "commonA",messageSendType = MessageSendType.SEND)
    public String sendCommonMsg() {
        return "commonA";
    }
    @GetMapping("/commonB")
    @CommonMessage(topic = "commonB", tag = "commonB",messageSendType = MessageSendType.SEND_ASYNC)
    public String sendAsyncMsg() {
        return "commonB";
    }
    @GetMapping("/commonC")
    @CommonMessage(topic = "commonC", tag = "commonC",messageSendType = MessageSendType.SEND_ONE_WAY)
    public String sendOneWayMessage() {
        return "commonC";
    }
}