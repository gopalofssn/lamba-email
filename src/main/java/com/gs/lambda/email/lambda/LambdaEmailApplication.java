package com.gs.lambda.email.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;

@SpringBootApplication
public class LambdaEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(LambdaEmailApplication.class, args);
	}
	
	@Bean
	public AmazonSNS getAmazonSNS() {
		AWSCredentials credentials = InstanceProfileCredentialsProvider.getInstance().getCredentials();
		AmazonSNS snsClient = AmazonSNSAsyncClientBuilder.standard()
				.withCredentials(InstanceProfileCredentialsProvider.getInstance()).withRegion(Regions.US_EAST_1)
				.build();
		return snsClient;
	}
}
