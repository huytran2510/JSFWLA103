package fa.training.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentForCreate {

    private String title;

    private String brief;

    private String content;

    private LocalDateTime createDate;
}
