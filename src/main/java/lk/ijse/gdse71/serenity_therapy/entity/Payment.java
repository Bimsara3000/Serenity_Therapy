package lk.ijse.gdse71.serenity_therapy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private String id;
    private double amount;
    @OneToOne
    private Patient patient;
    @OneToOne
    private Program program;
}
