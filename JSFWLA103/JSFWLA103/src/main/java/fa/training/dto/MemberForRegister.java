package fa.training.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberForRegister {
    @Size(min = 6, max = 20, message = "Length of username should be from 6 to 20")
    @NotEmpty(message = "Please enter username")
    private String username;

    @Email(message = "{user.email.invalid}")
    @NotEmpty(message = "Please enter email")
    private String email;

    @Size(min = 6, max = 20, message = "Length of password should be from 6 to 20")
    @NotEmpty(message = "Please enter password")
    private String password;

    @Size(min = 6, max = 20, message = "Length of password confirm should be from 6 to 20")
    @NotEmpty(message = "Please enter confirm password")
    private String passwordConfirm;

    private LocalDateTime createDate;


}
