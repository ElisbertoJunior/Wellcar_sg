package br.com.wellcar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany
    @JoinTable(
            name = "tb_report_auxiliary",
            joinColumns = {@JoinColumn(name = "id_report", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_order", referencedColumnName = "id")}
    )
    private List<OrderService> Orders;

    private LocalDate initialDate;

    private LocalDate finalDate;

    private Double totalBilled;
}
