package userWebService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import userWebService.dto.UserDto;
import userWebService.entity.User;
import userWebService.exception.ResourceNotFoundException;
import userWebService.repo.UserRepository;
import userWebService.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        // Assert.isNull(kisiDto.getAdi(),"Adi alani zorunludur!");
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setAge(userDto.getAge());
        user.setCountry(userDto.getCountry());
//        user.setBirthOfDate(userDto.getBirthOfDate());
        final User userDb = userRepository.save(user);
        userDto.setId(userDb.getId());
        return userDto;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll(Sort.by("id"));
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(it -> {
            UserDto userDto = new UserDto();
            userDto.setName(it.getName());
            userDto.setId(it.getId());
            userDto.setSurname(it.getSurname());
            userDto.setAge(it.getAge());
            userDto.setCountry(it.getCountry());
            userDtos.add(userDto);
        });
        return userDtos;
    }

    @Override
    public void delete(Long id) {
       // userRepository.deleteById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));;
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
        this.userRepository.delete(existingUser);
    }

    @Override
    public Page<User> findAll(int page, int size) {
        Pageable firstPageWithOneElement = PageRequest.of(page, size, Sort.by("id"));
        return userRepository.findAll(firstPageWithOneElement);

    }

    @Override
    public User insertUser(String name, String surname, Long age, String country) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        user.setCountry(country);
        final User userDb = userRepository.save(user);
        user.setId(userDb.getId());
        return user;
    }

    @PersistenceContext
    EntityManager em;

    @Override
    public UserDto update(Long id, UserDto userDto) {

        try {
            userDto.setId(em.find(User.class,id).getId());
            User user = new User();
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setAge(userDto.getAge());
            user.setCountry(userDto.getCountry());
            userRepository.save(user);
            return userDto;
        }
        catch (Exception ex){
            return create(userDto);

        }
    }
}
