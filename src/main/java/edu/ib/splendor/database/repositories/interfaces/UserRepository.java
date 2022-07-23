package edu.ib.splendor.database.repositories.interfaces;

import edu.ib.splendor.database.repositories.dtos.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDto, Long> {
}
