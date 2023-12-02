package org.mcnz.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProblemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeProblem;
    @Column(nullable = false)
    private String problemTypeName;
    @ManyToOne
    @JoinColumn(name = "id_offered_service", nullable = false)
    private OfferedService service;
}
