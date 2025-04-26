package lk.ijse.gdse71.serenity_therapy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDTO {
    private String id;
    private String name;
    private String duration;
    private double fee;
    private List<PatientDTO>patients;
    private Therapist therapist;
}
