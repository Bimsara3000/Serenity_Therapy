package lk.ijse.gdse71.serenity_therapy.entity;

import jakarta.persistence.*;
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
public class Patient {
    @Id
    private String id;
    private String name;
    private String email;
    private int phone;
    private LocalDate date;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Program>programs;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Session> sessions;
}
