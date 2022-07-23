package edu.ib.splendor.database.repositories.interfaces;

import edu.ib.splendor.database.repositories.dtos.GameDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameDto, Long> {
}
