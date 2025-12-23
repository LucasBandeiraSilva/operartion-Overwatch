package com.overwatch.logistic.domain.service;

import com.overwatch.logistic.domain.model.Ability;
import com.overwatch.logistic.domain.model.Agent;
import com.overwatch.logistic.infrastructure.messaging.subscriber.representation.DetailAgentRepresentation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Value("classpath:reports/overwatch-report.jrxml")
    private Resource report;

    @Value("classpath:reports/logo.png")
    private Resource logo;


    public byte[] generateReport( Agent agent ){
        try(InputStream inputStream = report.getInputStream()) {

            Map <String, Object> params = getParams(agent);

            List <String> abilityList = agent.getSupers().getAbilities();

            List<Ability> abilities = abilityList.stream().map(Ability::new).toList();

            var dataSource = new JRBeanCollectionDataSource(abilities);

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao exportar pdf: " + e.getMessage());
        }
    }

    @NotNull
    private Map <String, Object> getParams( Agent agent )  {
        Map<String,Object> params = new HashMap <>();

        params.put("NAME", agent.getName());
        params.put("AGENT_CODE", agent.getAgentCode());
        params.put("DATE_OF_BIRTH", agent.getDateOfBirth());
        params.put("AGENT_ROLE", agent.getAgentRole());


        params.put("NAME_SUPER", agent.getSupers().getNameSuper());
        params.put("BIRTH", agent.getSupers().getBirth());
        params.put("SUPER_CODE", agent.getSupers().getSuperCode());
        params.put("THREAT_LEVEL", agent.getSupers().getThreatLevel());
        String abilitiesText = String.join("\n", agent.getSupers().getAbilities());
        params.put("ABILITIES", abilitiesText);

        try {
            params.put("LOGO",logo.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return params;
    }
}
