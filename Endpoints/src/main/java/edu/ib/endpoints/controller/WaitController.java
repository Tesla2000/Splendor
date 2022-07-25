package edu.ib.endpoints.controller;

import edu.ib.endpoints.data.dtos.WaitDto;
import edu.ib.endpoints.service.WaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/wait")
public class WaitController {
    private final WaitService waitService;

    @Autowired
    public WaitController(WaitService waitService) {
        this.waitService = waitService;
    }

    @PostMapping
    public WaitDto save(@RequestBody WaitDto waitDto){
        return waitService.save(waitDto);
    }

    @GetMapping("/all")
    public Iterable<WaitDto> getAll(){
        return waitService.gatWaits();
    }

    @GetMapping
    public WaitDto getById(@RequestParam Long id){
        return waitService.getWait(id);
    }
}
