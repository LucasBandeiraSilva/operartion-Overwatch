package com.overwatch.logistic.interfaces;

import com.overwatch.logistic.infrastructure.storage.minio.BucketFile;
import com.overwatch.logistic.infrastructure.storage.minio.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService service;

    @PostMapping
    public ResponseEntity<String>uploadFile( @RequestParam("file")MultipartFile file ){
        try(InputStream inputStream = file.getInputStream()) {
            MediaType mediaType = MediaType.parseMediaType(file.getContentType());
            var bucketFile = new BucketFile(file.getOriginalFilename(),inputStream,mediaType, file.getSize());
            service.upload(bucketFile);
            return ResponseEntity.ok("Success when sending the archive!");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Error when sending the archive " + e.getMessage());
        }
    }
}
