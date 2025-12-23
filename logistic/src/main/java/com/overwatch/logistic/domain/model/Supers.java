package com.overwatch.logistic.domain.model;

import com.overwatch.logistic.domain.model.enums.ThreatLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supers {

    private Long id;
    private String nameSuper;
    private String birth;
    private String superCode;
    private List <String> abilities;
    private String threatLevel;

}
