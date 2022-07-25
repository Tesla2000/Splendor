package edu.ib.endpoints.data.interfaces;
import edu.ib.endpoints.data.dtos.AristocratDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AristocratRepository extends CrudRepository<AristocratDto, Long> {
}
