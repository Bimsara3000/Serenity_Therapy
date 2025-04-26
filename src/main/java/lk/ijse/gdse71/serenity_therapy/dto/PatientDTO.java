package lk.ijse.gdse71.serenity_therapy.dto;

import lk.ijse.gdse71.serenity_therapy.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private String id;
    private String name;
    private String email;
    private int phone;
    private LocalDate date;
    private List<Program>programs;
    private List<Session> sessions;

    public  PatientDTO(String id, String name, String email, int phone, LocalDate date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
    }
}
