package org.mcnz.jpa.models.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mcnz.jpa.models.OfferedService;


import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SpecialistUser extends User {

    @Column(nullable = false)
    private boolean state;

    @ManyToMany
    @JoinTable(
            name = "specialist_offeredService",
            joinColumns = @JoinColumn(name = "id_user", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_offered_service", nullable = false)
    )
    private List<OfferedService> offeredServices;


}
