package lk.ijse.gdse71.serenity_therapy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Therapist {
    @Id
    private String id;
    private String name;
    private String speciality;
    private String availability;
    @OneToMany(mappedBy = "therapist")
    private List<Program> programs;
    @OneToMany(mappedBy = "therapist")
    private List<Session> sessions;
}
