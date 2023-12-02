package org.mcnz.jpa.models;

import jakarta.persistence.*;
import lombok.*;
import org.mcnz.jpa.models.users.SpecialistUser;

import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncident;
    @Column(nullable = false)
    private String descriptionProblem;
    @Column(nullable = false)
    private String IncidentState;
    @Column
    private String specialistConsiderations;
    @Column (nullable = false)
    private LocalDate IncidentDate;
    @Column
    private LocalDate resolutionDate;

    @ManyToOne
    @JoinColumn(name = "id_type_problem", nullable = false)
    private ProblemType typeProblem;

    @ManyToOne
    @JoinColumn(name = "id_offered_service", nullable = false)
    private OfferedService offeredService;

    @ManyToOne
    @JoinColumn(name = "id_specialist", nullable = false)
    private SpecialistUser specialistAssigned;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;


}
