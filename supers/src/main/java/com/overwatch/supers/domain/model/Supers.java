package com.overwatch.supers.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_supers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Supers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private String superCode;

    @ElementCollection
    @CollectionTable(name = "tb_super_abilities", joinColumns = @JoinColumn(name = "super_id"))
    @Column(name = "ability")
    private List <String> abilities;

    @Enumerated(EnumType.STRING)
    private ThreatLevel threatLevel;

    private boolean active;

    @PrePersist
    private void prePersist() {
        this.active = true;
    }

}


