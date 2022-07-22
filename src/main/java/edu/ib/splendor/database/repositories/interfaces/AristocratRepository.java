package edu.ib.splendor.database.repositories.interfaces;

import edu.ib.splendor.database.repositories.projections.AristocratDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AristocratRepository extends CrudRepository<AristocratDto, Long> {
}
