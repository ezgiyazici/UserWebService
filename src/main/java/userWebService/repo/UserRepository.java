package userWebService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import userWebService.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
