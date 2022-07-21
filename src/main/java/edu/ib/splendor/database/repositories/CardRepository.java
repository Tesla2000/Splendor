package edu.ib.splendor.database.repositories;

import edu.ib.splendor.database.entities.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
}
