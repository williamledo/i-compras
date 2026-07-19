package io.github.williamledo.icompras.faturamento.bucket;

import java.io.InputStream;

import org.springframework.http.MediaType;

public record BucketFile(
		String bucketName,
		InputStream is,
		MediaType type,
		long size
) {

}
