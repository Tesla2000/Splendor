package edu.ib.splendor.database.repositories.interfaces;

import edu.ib.splendor.database.repositories.projections.CardDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<CardDto, Long> {
}
