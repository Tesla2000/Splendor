package edu.ib.splendor.database.repositories.interfaces;

import edu.ib.splendor.database.repositories.dtos.AristocratDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AristocratRepository extends CrudRepository<AristocratDto, Long> {
}
