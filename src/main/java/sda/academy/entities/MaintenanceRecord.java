package sda.academy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table (name = "Maintenance_Records")
public class MaintenanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "maintenance_date")
    private LocalDate maintenanceDate;

    @Column(name = "details")
    private String details;

//    @Column(name = "start_date")
//    private LocalDate startDateOfRepair;
//
//    @Column(name = "end_date")
//    private LocalDate endDateOfRepair;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
