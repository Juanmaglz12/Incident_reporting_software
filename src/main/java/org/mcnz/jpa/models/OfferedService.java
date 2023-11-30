package org.mcnz.jpa.models;

import jakarta.persistence.*;
import lombok.*;
import org.mcnz.jpa.models.users.SpecialistUser;
import org.mcnz.jpa.models.users.User;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class  OfferedService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOfferedService;
    @Column(nullable = false, unique = true)
    private String offeredServiceName;

    @ManyToMany(mappedBy = "contractedOfferedServices")
    private List<Client> clients;

    @ManyToMany(mappedBy = "offeredServices")
    private List<SpecialistUser> specialistUsers;


}
