package fa.training.dto;

import lombok.*;


import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberForUpdate {
    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String description;

    private LocalDateTime updateTime;
}
