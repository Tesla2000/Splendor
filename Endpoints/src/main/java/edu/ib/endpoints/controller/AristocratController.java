package edu.ib.endpoints.controller;

import edu.ib.endpoints.data.dtos.AristocratDto;
import edu.ib.endpoints.service.AristocratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/aristocrat")
public class AristocratController {
    private final AristocratService aristocratService;

    @Autowired
    public AristocratController(AristocratService aristocratService) {
        this.aristocratService = aristocratService;
    }

    @GetMapping("/all")
    public Iterable<AristocratDto> getAll(){
        return aristocratService.getAristocrats();
    }

    @GetMapping
    public AristocratDto getById(@RequestParam Long id){
        return aristocratService.getAristocrat(id);
    }

    @PostMapping
    public AristocratDto save(@RequestBody AristocratDto aristocratDto) {
        return aristocratService.save(aristocratDto);
    }
}
