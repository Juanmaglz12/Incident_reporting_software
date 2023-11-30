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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    @Column(nullable = false)
    private String businessName;
    @Column(nullable = false, unique = true)
    private String cuit;
    @Column(nullable = false, unique = true)
    private String mail;

    @ManyToMany
    @JoinTable(
            name = "client_service",
            joinColumns = @JoinColumn(name = "id_cliente", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_service", nullable = false)
    )
    private List<OfferedService> contractedOfferedServices;


}
