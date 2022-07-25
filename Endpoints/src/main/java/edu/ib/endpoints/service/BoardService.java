package edu.ib.endpoints.service;

import edu.ib.endpoints.data.dtos.BoardDto;
import edu.ib.endpoints.data.interfaces.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardDto getBoard(Long id){
        return boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Iterable<BoardDto> getBoards(){
        return boardRepository.findAll();
    }

    public BoardDto save(BoardDto boardDto){
        return boardRepository.save(boardDto);
    }
}
