package edu.ib.endpoints.data.interfaces;
import edu.ib.endpoints.data.dtos.CardDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<CardDto, Long> {
}
