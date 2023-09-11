package br.com.wellcar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private LocalDateTime creationDate;

    @ManyToMany
    @JoinTable(
            name = "tb_auxiliary",
            joinColumns = {@JoinColumn(name = "id_budget", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_product", referencedColumnName = "id")}
    )
    private List<Product> products;

    @ManyToMany
    @JoinTable(
            name = "tb_lab_auxiliary",
            joinColumns = {@JoinColumn(name = "id_budget", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_labor", referencedColumnName = "id")}
    )
    private List<Labor> labors;

    private Boolean isApproved;

    private Double totalValue;

}
