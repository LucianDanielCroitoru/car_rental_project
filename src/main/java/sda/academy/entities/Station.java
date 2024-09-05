package sda.academy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "Stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Location_Name")
    private String locationName;

    private String address;

    @OneToMany (mappedBy = "station")
    private List<Car> cars = new ArrayList<>();
}