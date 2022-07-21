package edu.ib.splendor.database.repositories;

import edu.ib.splendor.database.entities.Aristocrat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AristocratRepository extends CrudRepository<Aristocrat, Long> {
}
