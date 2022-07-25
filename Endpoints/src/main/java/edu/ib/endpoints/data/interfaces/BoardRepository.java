package edu.ib.endpoints.data.interfaces;

import edu.ib.endpoints.data.dtos.BoardDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<BoardDto, Long> {
}
