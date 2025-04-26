package lk.ijse.gdse71.serenity_therapy.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTM {
    private String id;
    private String name;
    private String role;
    private String password;
}
