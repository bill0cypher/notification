package com.finloader.notification.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AWSSQSConfig {


    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync sqsAsync) {
        return new QueueMessagingTemplate(sqsAsync);
    }
}
