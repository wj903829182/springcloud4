package com.jack.service;

import com.alibaba.rocketmq.common.message.MessageExt;
import com.jack.inter.MessageProcessor;
import org.springframework.stereotype.Service;

/**
 * Created by jack on 2017/11/16.
 */
@Service
public class MessageProcessorImplTest implements MessageProcessor {
    @Override
    public boolean handleMessage(MessageExt messageExt) {
        System.out.println("receive : " + messageExt.toString());
        return true;
    }
}
