package edu.ib.splendor.database.repositories;

import edu.ib.splendor.database.entities.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {
}
