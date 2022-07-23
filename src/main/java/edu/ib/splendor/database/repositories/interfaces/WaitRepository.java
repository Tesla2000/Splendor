package edu.ib.splendor.database.repositories.interfaces;

import edu.ib.splendor.database.repositories.dtos.WaitDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitRepository extends CrudRepository<WaitDto, Long> {
}
