package fa.training.entities;



import fa.training.dto.ContentForCreate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "content", schema = "dbo")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String brief;

    @Column
    private String content;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private Member member;

    public Content(ContentForCreate dto){
        this.title= dto.getTitle();
        this.brief = dto.getBrief();
        this.content = dto.getContent();
        this.createDate = dto.getCreateDate();
    }

}
