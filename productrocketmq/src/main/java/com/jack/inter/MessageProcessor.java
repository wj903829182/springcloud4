package com.jack.inter;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * Created by jack on 2017/11/16.
 */
public interface MessageProcessor {
    /**
     * 处理消息的接口
     * @param messageExt
     * @return
     */
    public boolean handleMessage(MessageExt messageExt);
}
