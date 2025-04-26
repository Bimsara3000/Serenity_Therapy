package lk.ijse.gdse71.serenity_therapy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    private String id;
    private LocalDate date;
    @ManyToOne
    private Therapist therapist;
    @ManyToOne
    private Patient patient;
}
