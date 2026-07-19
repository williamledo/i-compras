package io.github.williamledo.icompras.faturamento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class BucketConfig {

	@Autowired
	MinioProps minioProps;
	
	@Bean
	public MinioClient bucketClient() {
		return MinioClient.builder()
				.endpoint(minioProps.getUrl())
				.credentials(minioProps.getAccessKey(), minioProps.getSecretKey())
				.build();
	}
	
}
