package com.jack.rocketmqtransaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;

import java.util.Date;
import java.util.Random;

/**
 * Created by jack on 2017/11/30.
 * 执行本地事务
 */
public class TransactionExecuterImpl implements LocalTransactionExecuter {
    @Override
    public LocalTransactionState executeLocalTransactionBranch(Message message, Object arg) {
        try {
            //DB操作，应该带上事务，service-dao
            //如果数据库操作失败，需要回滚，同时返回RMQ一个失败消息，意味着消费者无法消费到这条失败的消息
            //如果成功，需要告诉RMQ一个成功的消息，意味着消费者将读取到消息，
            //arg就是attachment
            if (new Random().nextInt(3) == 2) {
                int a = 2 / 0;
            }
            System.out.println(new Date()+"本地事务执行成功，发送确认消息");
        } catch (Exception e) {
            System.out.println(new Date()+"本地事务执行失败");
            e.printStackTrace();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        //这种消息意味着事务消息将不会被消费者读取到
        //return LocalTransactionState.UNKNOW;
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
