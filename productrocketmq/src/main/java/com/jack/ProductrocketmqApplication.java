package com.jack;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * http://blog.csdn.net/qq_22841811/article/details/67783473
 * http://www.jianshu.com/p/8813ec02926a
 * http://blog.csdn.net/forearrow/article/details/47778497
 * http://blog.csdn.net/tvwr8ofv0p/article/details/78293658
 * http://blog.jobbole.com/89140/?spm=5176.100239.blogcont46662.3.KJJib1
 * http://blog.csdn.net/gredn/article/details/73732871
 * http://blog.csdn.net/gredn/article/details/73732861
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableTransactionManagement
@EnableRedisHttpSession
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
		/*System.out.println("run........begin");
		DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
		Message msg = new Message("TEST",// topic
				"TEST",// tag
				"KKK",//key用于标识业务的唯一性
				("Hello RocketMQ !!!!!!!!!!" ).getBytes()// body 二进制字节数组
		);
		SendResult result = defaultMQProducer.send(msg);
		System.out.println(result);
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();*/
	}
}
