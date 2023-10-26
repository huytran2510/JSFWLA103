package fa.training.entities;



import fa.training.dto.MemberForRegister;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "member", schema = "dbo")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column(name = "username", unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String description;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime updateTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private Set<Content> contentSet;

    @ManyToMany
    private Set<Role> roles;

    public Member(MemberForRegister memberForRegister) {
        this.username = memberForRegister.getUsername();
        this.password = memberForRegister.getPassword();
        this.email = memberForRegister.getEmail();
        this.createDate = memberForRegister.getCreateDate();
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
