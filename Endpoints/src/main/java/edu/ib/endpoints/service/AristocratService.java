package edu.ib.endpoints.service;
import edu.ib.endpoints.data.dtos.AristocratDto;
import edu.ib.endpoints.data.interfaces.AristocratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AristocratService {
    private final AristocratRepository aristocratRepository;

    @Autowired
    public AristocratService(AristocratRepository aristocratRepository) {
        this.aristocratRepository = aristocratRepository;
    }

    public Iterable<AristocratDto> getAristocrats(){
        return aristocratRepository.findAll();
    }

    public AristocratDto getAristocrat(Long id){
        return aristocratRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public AristocratDto save(AristocratDto aristocratDto){
        return aristocratRepository.save(aristocratDto);
    }

}
