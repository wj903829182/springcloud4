package com.jack.rocketmqtransaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;

import java.util.Date;

/**
 * Created by jack on 2017/11/30.
 * 事务消息生产者
 * http://www.jianshu.com/p/453c6e7ff81c
 * http://blog.csdn.net/column/details/learningrocketmq.html
 */
public class TProducer {
    public static void main(String[] args) {
        TransactionMQProducer producer = new TransactionMQProducer("transactionProduceGroup");
        producer.setNamesrvAddr("192.168.9.105:9876");
        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        //设置事务检查监听
        producer.setTransactionCheckListener(transactionCheckListener);
        TransactionExecuterImpl transactionExecuter = new TransactionExecuterImpl();
        try {
            //启动消息生产者
            producer.start();
            Message message1 = new Message("TransactionTopic","Tag","key",
                    "hello rocketmq ,I am transaction message 1".getBytes());
            Message message2 = new Message("TransactionTopic","Tag","key",
                    "hello rocketmq ,I am transaction message 2".getBytes());
            SendResult sendResult = producer.sendMessageInTransaction(message1, transactionExecuter, null);
            System.out.println(new Date()+",msg1 = "+sendResult);
            sendResult = producer.sendMessageInTransaction(message2, transactionExecuter, null);
            System.out.println(new Date()+",msg2 = "+sendResult);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        //producer.shutdown();

    }
}
