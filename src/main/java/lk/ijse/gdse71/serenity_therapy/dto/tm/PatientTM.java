package lk.ijse.gdse71.serenity_therapy.dto.tm;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientTM {
    private String id;
    private String name;
    private String program;
    private String email;
    private int phone;
}
