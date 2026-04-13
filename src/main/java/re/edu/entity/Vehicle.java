package re.edu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String color;
    private TypeVehicle typeVehicle;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTicket;


}
