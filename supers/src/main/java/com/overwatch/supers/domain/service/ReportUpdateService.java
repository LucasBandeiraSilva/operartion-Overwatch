package com.overwatch.supers.domain.service;

import com.overwatch.supers.domain.exception.BusinessException;
import com.overwatch.supers.infrastructure.SupersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportUpdateService {

    private final SupersRepository repository;

    @Transactional
    public void updateReport( String superCode, String urlReport){
        repository.findBySuperCode(superCode).ifPresentOrElse(supers -> {
            if (urlReport != null) supers.setUrlReport(urlReport);
        },()->{throw new BusinessException("report not found");});
    }
}
