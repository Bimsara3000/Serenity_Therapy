package lk.ijse.gdse71.serenity_therapy.dto;

import jakarta.persistence.Entity;
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
    private String id;
    private String name;
    private String speciality;
    private String availability;
    private List<ProgramDTO> programs;
    private List<Session> sessions;
}
