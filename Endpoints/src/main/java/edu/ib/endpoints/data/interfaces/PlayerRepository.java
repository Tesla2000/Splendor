package edu.ib.endpoints.data.interfaces;
import edu.ib.endpoints.data.dtos.PlayerDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerDto, Long> {
}
