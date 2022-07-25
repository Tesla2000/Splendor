package edu.ib.endpoints.data.interfaces;
import edu.ib.endpoints.data.dtos.GameDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameDto, Long> {
}
