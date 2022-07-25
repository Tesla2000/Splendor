package edu.ib.endpoints.service;

import edu.ib.endpoints.data.dtos.WaitDto;
import edu.ib.endpoints.data.interfaces.WaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaitService {
    private final WaitRepository waitRepository;

    @Autowired
    public WaitService(WaitRepository waitRepository) {
        this.waitRepository = waitRepository;
    }

    public WaitDto save(WaitDto waitDto){
        return waitRepository.save(waitDto);
    }

    public WaitDto getWait(Long id){
        return waitRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public Iterable<WaitDto> gatWaits(){
        return waitRepository.findAll();
    }
}
