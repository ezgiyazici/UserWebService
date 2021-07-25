package userWebService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
@ToString
public class User {

    @Id
    @SequenceGenerator(name="seq_kisi",allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi",strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(length=20, name="name",nullable = false)
    private String name;

    @NotNull
    @Column(length=20, name="surname",nullable = false)
    private String surname;

    @NotNull
    @Column(name="age",nullable = false)
    private Long age;

    @NotNull
    @Column(length=20, name="country",nullable = false)
    private String country;

//    @DateTimeFormat
//    @NotNull
//    @Column(name="birthOfDate")
//    @JsonFormat(pattern = "dd-MM-yyyy")
//    private Date birthOfDate;

//    @OneToOne
//    @JoinColumn(name="user_info")
//    private UserInformation userInformation;
}

