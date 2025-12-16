package com.overwatch.logistic.infrastructure.storage.minio.service;

import com.overwatch.logistic.infrastructure.storage.minio.BucketFile;
import com.overwatch.logistic.infrastructure.storage.minio.MinioProps;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final MinioClient client;
    private final MinioProps minioProps;

    public void upload( BucketFile file ) {
        try {
            var object = PutObjectArgs.builder()
                    .bucket(minioProps.getBucketName())
                    .object(file.name())
                    .stream(file.inputStream(), file.size(), -1)
                    .contentType(file.mediaType().toString())
                    .build();

            client.putObject(object);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public String getUrl(String file){
        try {
            var object = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(minioProps.getBucketName())
                    .object(file)
                    .expiry(1, TimeUnit.HOURS)
                    .build();

            return client.getPresignedObjectUrl(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
