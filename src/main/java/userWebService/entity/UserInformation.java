package userWebService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="usersInformation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
@ToString
public class UserInformation {

    @Id
    @SequenceGenerator(name="seq_kisi_bilgi",allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi_bilgi",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length=500, name="address")
    private String address;

    @NotNull
    @Column(length=20, name="mothersName",nullable = false)
    private String mothersName;

    @NotNull
    @Column(length=20, name="fathersName",nullable = false)
    private String fathersName;

    @DateTimeFormat
    @NotNull
    @Column(name="birthOfDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthOfDate;

    @OneToOne
    @JoinColumn(name="user_info")
    private User user;
}
