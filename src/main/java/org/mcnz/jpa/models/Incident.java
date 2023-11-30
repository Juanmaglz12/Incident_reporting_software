package org.mcnz.jpa.models;

import jakarta.persistence.*;
import lombok.*;
import org.mcnz.jpa.models.users.SpecialistUser;


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
    private String typeProblem;
    @Column(nullable = false)
    private String problemDescription;
    @Column(nullable = true)
    private int hoursToResolveIssue;
    @Column(nullable = false)
    private String IncidentState;

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
