package lk.ijse.gdse71.serenity_therapy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String id;
    private LocalDate date;
    private Therapist therapist;
    private PatientDTO patient;
}
