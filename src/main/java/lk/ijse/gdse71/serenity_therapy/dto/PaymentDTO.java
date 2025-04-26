package lk.ijse.gdse71.serenity_therapy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String id;
    private double amount;
    private PatientDTO patient;
    private ProgramDTO program;
}
