package edu.ib.splendor.database.repositories.interfaces;

import edu.ib.splendor.database.repositories.projections.PlayerDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerDto, Long> {
}
