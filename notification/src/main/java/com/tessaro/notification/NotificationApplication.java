package com.tessaro.notification;

import com.tessaro.amqp.producer.RabbitMQMessageProducer;
import com.tessaro.notification.config.amqp.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(
//        scanBasePackages = {
//                "com.tessaro.notification",
//                "com.tessaro.amqp"
//        }
//)
@SpringBootApplication
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner (
//            RabbitMQMessageProducer producer,
//            NotificationConfig notificationConfig
//    ) {
//      return args -> {
//          producer.publish(
//                  "message, this message also can be a object",
//                  notificationConfig.getInternalExchange(),
//                  notificationConfig.getInternalNotificationRoutingKey()
//          );
//        };
//    };
}
