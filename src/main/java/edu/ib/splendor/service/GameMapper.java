package edu.ib.splendor.service;

import edu.ib.splendor.database.entities.*;
import edu.ib.splendor.database.repositories.interfaces.AristocratRepository;
import edu.ib.splendor.database.repositories.interfaces.BoardRepository;
import edu.ib.splendor.database.repositories.interfaces.CardRepository;
import edu.ib.splendor.database.repositories.interfaces.PlayerRepository;
import edu.ib.splendor.database.repositories.projections.AristocratDto;
import edu.ib.splendor.database.repositories.projections.BoardDto;
import edu.ib.splendor.database.repositories.projections.CardDto;
import edu.ib.splendor.database.repositories.projections.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    private final AristocratRepository aristocratRepository;
    private final BoardRepository boardRepository;
    private final CardRepository cardRepository;
    private final PlayerRepository playerRepository;


    @Autowired
    public GameMapper(AristocratRepository aristocratRepository, BoardRepository boardRepository, CardRepository cardRepository, PlayerRepository playerRepository) {
        this.aristocratRepository = aristocratRepository;
        this.boardRepository = boardRepository;
        this.cardRepository = cardRepository;
        this.playerRepository = playerRepository;
    }


    public void saveGame(Board board){
        BoardDto boardDto = new BoardDto();
        boardDto.setBlue(board.getStored(Gem.BLUE));
        boardDto.setGreen(board.getStored(Gem.GREEN));
        boardDto.setRed(board.getStored(Gem.RED));
        boardDto.setBrown(board.getStored(Gem.BROWN));
        boardDto.setWhite(board.getStored(Gem.WHITE));
        boardDto.setGold(board.getStored(Gem.GOLD));
        boardDto.setId(1L);
        boardRepository.save(boardDto);
        int playerCounter = 0;
        for (Player player: board.getPlayers()){
            PlayerDto playerDto = new PlayerDto();
            playerDto.setPoints(player.getPoints());
            playerDto.setBoard(boardDto);
            playerDto.setName(player.getName());
            playerDto.setBlue(player.getPossession().get(Gem.BLUE));
            playerDto.setGreen(player.getPossession().get(Gem.GREEN));
            playerDto.setRed(player.getPossession().get(Gem.RED));
            playerDto.setBrown(player.getPossession().get(Gem.BROWN));
            playerDto.setWhite(player.getPossession().get(Gem.WHITE));
            playerDto.setGold(player.getPossession().get(Gem.GOLD));
            playerDto.setQueuePosition(playerCounter);
            playerDto.setId((long)playerCounter);
            playerCounter++;
            playerRepository.save(playerDto);
            for (Card card: player.getReserve()){
                CardDto cardDto = copyCard(card);
                cardDto.setReserve(true);
                cardDto.setPlayerProjection(playerDto);
                cardDto.setId(1L);
                cardRepository.save(cardDto);
            }
            for (Card card: player.getDeck()){
                CardDto cardDto = copyCard(card);
                cardDto.setReserve(false);
                cardDto.setPlayerProjection(playerDto);
                cardDto.setId(1L);
                cardRepository.save(cardDto);
            }
        }
        for (Tier tier: Tier.values())
            if (!tier.equals(Tier.RESERVE)) {
                for (Card card : board.getTradeRow().getCardsHidden().get(tier)) {
                    CardDto cardDto = copyCard(card);
                    cardDto.setBoard(boardDto);
                    cardDto.setVisible(false);
                    cardDto.setId(1L);
                    cardRepository.save(cardDto);
                }
                for (Card card : board.getTradeRow().getCardsVisible().get(tier)) {
                    CardDto cardDto = copyCard(card);
                    cardDto.setBoard(boardDto);
                    cardDto.setVisible(true);
                    cardDto.setId(1L);
                    cardRepository.save(cardDto);
                }
            }
        for (Aristocrat aristocrat: board.getAristocrats()){
            AristocratDto aristocratDto = new AristocratDto();
            aristocratDto.setBlue(aristocratDto.getBlue());
            aristocratDto.setBrown(aristocratDto.getBrown());
            aristocratDto.setRed(aristocratDto.getRed());
            aristocratDto.setGreen(aristocratDto.getGreen());
            aristocratDto.setWhite(aristocratDto.getWhite());
            aristocratDto.setImage(aristocrat.getImage());
            aristocratDto.setBoardDto(boardDto);
            aristocratDto.setId(1L);
            aristocratRepository.save(aristocratDto);
        }
    }

    private CardDto copyCard(Card card){
        CardDto cardDto = new CardDto();
        cardDto.setBlue(card.getCost().get(Gem.BLUE));
        cardDto.setGreen(card.getCost().get(Gem.GREEN));
        cardDto.setRed(card.getCost().get(Gem.RED));
        cardDto.setBrown(card.getCost().get(Gem.BROWN));
        cardDto.setWhite(card.getCost().get(Gem.WHITE));
        cardDto.setPicture(card.getPicture());
        cardDto.setPoints(card.getPoints());
        cardDto.setTier(card.getTier());
        return cardDto;
    }
}
