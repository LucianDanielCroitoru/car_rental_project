package sda.academy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String licensePlate;

    private String model;

    private int pricePerDay;

    @OneToMany (mappedBy = "car")
    private List<Reservation> reservationList = new ArrayList<>();
    @OneToMany (mappedBy = "car")
    private List<MaintenanceRecord> maintenanceRecordList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}
