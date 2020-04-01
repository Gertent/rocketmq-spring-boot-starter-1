package com.example.demo.ProducerController;

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
public class ProducerCommonController {

    @GetMapping("/commonA")
    @CommonMessage(topic = "topicCommonA", tag = "tagCommonA",messageSendType = MessageSendType.SEND)
    public String sendCommonMsg() {
        return "send commonA message";
    }
    @GetMapping("/commonB")
    @CommonMessage(topic = "topicCommonB", tag = "tagCommonB",messageSendType = MessageSendType.SEND_ASYNC, callback = ProducerSendCallback.class)
    public String sendAsyncMsg() {
        return "send commonB message";
    }
    @GetMapping("/commonC")
    @CommonMessage(topic = "topicCommonC", tag = "tagCommonC",messageSendType = MessageSendType.SEND_ONE_WAY)
    public String sendOneWayMessage() {
        return "send commonC message";
    }


}