package com.jack.rocketmqtransaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * Created by jack on 2017/11/30.
 * 未决事务，服务器回查客户端,3.0.8-3.1.4有效，以后的版本去掉了事务的回查机制
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
        System.out.println("server checking TrMsg : "+messageExt.toString());
        //由于RMQ迟迟没有收到消息的确认消息，因此主动询问这条prepare消息是否正常
        //可以查询数据库，看这条消息是否已经处理
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
