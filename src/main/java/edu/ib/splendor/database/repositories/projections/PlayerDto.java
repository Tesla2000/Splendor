package edu.ib.splendor.database.repositories.projections;

import javax.persistence.*;

@Entity
public class PlayerDto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer points;
    private String name;
    private int queuePosition;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardDto board;

    public BoardDto getBoard() {
        return board;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(int queuePosition) {
        this.queuePosition = queuePosition;
    }

    public void setBoard(BoardDto board) {
        this.board = board;
    }
}
