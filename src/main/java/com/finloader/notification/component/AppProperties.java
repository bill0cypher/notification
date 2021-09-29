package com.finloader.notification.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AppProperties {

    @Value("${aws.access-key}")
    private String awsAccessKey;

    @Value("${aws.access-secret-key}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.sqs.messaging-channel}")
    private String awsMessagingChannel;

    @Value("${aws.sqs.service-endpoint}")
    private String awsSQSServiceEndpoint;
}
