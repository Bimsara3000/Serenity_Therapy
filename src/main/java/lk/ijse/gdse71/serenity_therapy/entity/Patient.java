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
    @ManyToMany
    private List<Program>programs;
    @OneToMany(mappedBy = "patient")
    private List<Session> sessions;
}
