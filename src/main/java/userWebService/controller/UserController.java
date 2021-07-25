package userWebService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userWebService.dto.UserDto;
import userWebService.entity.User;
import userWebService.service.UserService;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:4201")
@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    @Query(value="SELECT * FROM users ORDER BY id",nativeQuery = true)
    public ResponseEntity<List<UserDto>> get(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto){
        return userService.create(userDto);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        userService.delete(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateById(@PathVariable Long id,@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.update(id,userDto));
    }

   /* @Query(value="INSERT INTO users (name,surname,age,country) VALUES (:name , :surname , :age , :country)",nativeQuery = true)
    public ResponseEntity<User> insertUserByBody(@Param("name") String name,@Param("surname") String surname,@Param("age") Long age ,@Param("country") String country){
        return ResponseEntity.ok(userService.insertUser(name,surname,age,country));
    }*/
    /*@GetMapping("/{pageNumber}")
    @Query(value="SELECT * FROM users ORDER BY id",nativeQuery = true)
    public ResponseEntity<Page<User>> findAllPageable(@PathVariable int pageNumber){
        return ResponseEntity.ok(userService.findAll(pageNumber-1,1));
    }*/
}
