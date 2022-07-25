package edu.ib.endpoints.data.interfaces;
import edu.ib.endpoints.data.dtos.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDto, Long> {
}
