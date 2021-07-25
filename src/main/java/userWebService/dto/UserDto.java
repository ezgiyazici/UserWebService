package userWebService.dto;

import lombok.Data;
import userWebService.entity.UserInformation;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String surname;

    private Long age;

    private String country;
//    private Date birthOfDate;
    //private UserInformation userInformation;
}
