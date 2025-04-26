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
public class Program {
    @Id
    private String id;
    private String name;
    private String duration;
    private double fee;
    @ManyToMany(mappedBy = "programs")
    private List<Patient>patients;
    @ManyToOne
    private Therapist therapist;
}
