package com.finloader.notification.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.finloader.notification.component.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NotificationConfig {

    private final AppProperties appProperties;

    public AmazonSQSAsync sqsClientAsync() {

        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                appProperties.getAwsSQSServiceEndpoint(),
                                appProperties.getAwsRegion()
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        appProperties.getAwsAccessKey(),
                                        appProperties.getAwsSecretKey()
                                )
                        )
                ).build();
    }
    @Bean
    public QueueMessagingTemplate messagingTemplate(AmazonSQSAsync sqsAsync) {
        QueueMessagingTemplate messagingTemplate = new QueueMessagingTemplate(sqsClientAsync());
        messagingTemplate.setDefaultDestination(new QueueMessageChannel(sqsClientAsync(), appProperties.getAwsMessagingChannel()));
        return messagingTemplate;
    }
}
