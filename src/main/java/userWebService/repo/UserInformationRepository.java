package userWebService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import userWebService.entity.UserInformation;

public interface UserInformationRepository  extends JpaRepository<UserInformation,Long> {
}
