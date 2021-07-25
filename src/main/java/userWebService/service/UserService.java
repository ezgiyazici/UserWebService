package userWebService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import userWebService.dto.UserDto;
import userWebService.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto create(UserDto userDto);

    void delete(Long id);

    UserDto update(Long id,UserDto userDto);

    Page<User> findAll(int page,int size);

    User insertUser(String name,String surname,Long age,String country);
}
