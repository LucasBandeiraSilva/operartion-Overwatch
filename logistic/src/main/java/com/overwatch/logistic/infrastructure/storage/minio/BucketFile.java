package com.overwatch.logistic.infrastructure.storage.minio;

import org.springframework.http.MediaType;

import java.io.InputStream;

public record BucketFile(String name, InputStream inputStream, MediaType mediaType, long size) {
}
