package edu.ib.endpoints.controller;

import edu.ib.endpoints.data.dtos.BoardDto;
import edu.ib.endpoints.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public BoardDto getById(@RequestParam Long id){
        return boardService.getBoard(id);
    }

    @GetMapping("/all")
    public Iterable<BoardDto> getAll(){
        return boardService.getBoards();
    }

    @PostMapping
    public BoardDto save(@RequestBody BoardDto boardDto){
        return boardService.save(boardDto);
    }
}
