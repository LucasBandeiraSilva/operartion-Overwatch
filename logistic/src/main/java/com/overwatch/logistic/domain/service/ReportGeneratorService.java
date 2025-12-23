package com.overwatch.logistic.domain.service;

import com.overwatch.logistic.domain.model.Agent;
import com.overwatch.logistic.infrastructure.storage.minio.BucketFile;
import com.overwatch.logistic.infrastructure.storage.minio.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportGeneratorService {

    private final ReportService reportService;
    private final BucketService bucketService;

    public void generateReport( Agent agent ){

        String nameArchive = String.format("OW_Report_%s.pdf",agent.getAgentCode());

        byte[] byteArray = reportService.generateReport(agent);

        var file = new BucketFile(nameArchive,new ByteArrayInputStream(byteArray), MediaType.APPLICATION_PDF,byteArray.length);

        bucketService.upload(file);
        log.info("Report already generated!");
    }
}
