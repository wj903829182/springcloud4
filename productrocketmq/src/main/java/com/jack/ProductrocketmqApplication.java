package com.jack;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * http://blog.csdn.net/qq_22841811/article/details/67783473
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductrocketmqApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProductrocketmqApplication.class, args);
	}

	/**
	 * spring boot启动完成后执行的方法
	 * @param strings
	 * @throws Exception
	 */
	@Override
	public void run(String... strings) throws Exception {
		System.out.println("run........begin");
		DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
		Message msg = new Message("TEST",// topic
				"TEST",// tag
				"KKK",//key用于标识业务的唯一性
				("Hello RocketMQ !!!!!!!!!!" ).getBytes()// body 二进制字节数组
		);
		SendResult result = defaultMQProducer.send(msg);
		System.out.println(result);
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
	}
}
