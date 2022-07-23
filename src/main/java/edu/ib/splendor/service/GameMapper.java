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
import edu.ib.splendor.service.AI.AIManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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

    public Board recreateGame(Long id) throws NoSuchIdException, IOException {
        BoardDto boardDto = boardRepository.findById(id).orElseThrow(NoSuchIdException::new);
        List<CardDto> cardDtos = (List<CardDto>) cardRepository.findAll();
        List<CardDto> list = new ArrayList<>();
        for (CardDto dto : cardDtos) {
            if (dto.getBoard().getId().equals(boardDto.getId())) {
                list.add(dto);
            }
        }
        cardDtos = list;
        List<AristocratDto> aristocratDtos = new ArrayList<>();
        for (AristocratDto dto : aristocratRepository.findAll()) {
            if (dto.getBoardDto().getId().equals(boardDto.getId())) {
                aristocratDtos.add(dto);
            }
        }
        ArrayList<ArrayList<Card>> hiddenCards = new ArrayList<>();
        ArrayList<ArrayList<Card>> visibleCards = new ArrayList<>();
        HashMap<Long, ArrayList<Card>> decks = new HashMap<>();
        HashMap<Long, ArrayList<Card>> reserves = new HashMap<>();
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (PlayerDto dto : playerRepository.findAll()) {
            if (dto.getBoard().getId().equals(boardDto.getId())) {
                playerDtos.add(dto);
            }
        }
        for (PlayerDto dto: playerDtos){
            decks.put(dto.getId(), new ArrayList<>());
            reserves.put(dto.getId(), new ArrayList<>());
        }
        for (int i = 0; i < 3; i++) {
            hiddenCards.add(new ArrayList<>());
            visibleCards.add(new ArrayList<>());
        }
        for (CardDto cardDto : cardDtos) {
            if (cardDto.getPlayerDto() == null) {
                switch (cardDto.getTier()) {
                    case FIRST:
                        if (cardDto.getVisible()) {
                            visibleCards.get(0).add(dtoToCard(cardDto));
                        } else {
                            hiddenCards.get(0).add(dtoToCard(cardDto));
                        }
                        break;
                    case SECOND:
                        if (cardDto.getVisible()) {
                            visibleCards.get(1).add(dtoToCard(cardDto));
                        } else {
                            hiddenCards.get(1).add(dtoToCard(cardDto));
                        }
                        break;
                    case THIRD:
                        if (cardDto.getVisible()) {
                            visibleCards.get(2).add(dtoToCard(cardDto));
                        } else {
                            hiddenCards.get(2).add(dtoToCard(cardDto));
                        }
                        break;
                }
            } else {
                if (cardDto.getReserve()){
                    reserves.get(cardDto.getPlayerDto().getId()).add(dtoToCard(cardDto));
                }else {
                    decks.get(cardDto.getPlayerDto().getId()).add(dtoToCard(cardDto));
                }
            }
        }
        playerDtos.sort(Comparator.comparingInt(PlayerDto::getQueuePosition));
        ArrayList<Player>players =new ArrayList<>();
        for (PlayerDto playerDto: playerDtos){
            Player player = new Player(playerDto.getName(), playerDto.getRed(), playerDto.getGreen(), playerDto.getBlue(), playerDto.getBrown(), playerDto.getWhite(), playerDto.getGold(), decks.get(playerDto.getId()), reserves.get(playerDto.getId()));
            if (playerDto.getAi()==null)
            players.add(player);
            else players.add(new PlayerWithNodes(player, AIManager.readNodesFromFile(playerDto.getAi()), playerDto.getAi()));
        }
        ArrayList<Aristocrat> aristocrats = new ArrayList<>();
        for (AristocratDto aristocratDto: aristocratDtos){
            aristocrats.add(new Aristocrat(aristocratDto.getRed(), aristocratDto.getGreen(), aristocratDto.getBlue(), aristocratDto.getBrown(), aristocratDto.getWhite(), aristocratDto.getImage()));
        }
        TradeRow tradeRow = new TradeRow(hiddenCards.get(0), hiddenCards.get(1), hiddenCards.get(2), visibleCards.get(0), visibleCards.get(1), visibleCards.get(2));
        return new Board(tradeRow, players, aristocrats,boardDto.getRed(), boardDto.getGreen(), boardDto.getBlue(), boardDto.getBrown(), boardDto.getWhite(), boardDto.getGold());
    }


    public long saveGame(Board board) {
        LocalDateTime creationTime = LocalDateTime.now();
        BoardDto boardDto = new BoardDto();
        boardDto.setBlue(board.getStored(Gem.BLUE));
        boardDto.setGreen(board.getStored(Gem.GREEN));
        boardDto.setRed(board.getStored(Gem.RED));
        boardDto.setBrown(board.getStored(Gem.BROWN));
        boardDto.setWhite(board.getStored(Gem.WHITE));
        boardDto.setGold(board.getStored(Gem.GOLD));
        boardDto.setCreation(creationTime);
        boardRepository.save(boardDto);
        int playerCounter = 0;
        for (Player player : board.getPlayers()) {
            PlayerDto playerDto = new PlayerDto();
            if (player instanceof PlayerWithNodes)
                playerDto.setAi(((PlayerWithNodes)player).getNodesFile());
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
            playerDto.setCreation(creationTime);
            playerCounter++;
            playerRepository.save(playerDto);
            for (Card card : player.getReserve()) {
                CardDto cardDto = cardToDto(card);
                cardDto.setReserve(true);
                cardDto.setPlayerDto(playerDto);
                cardDto.setBoard(boardDto);
                cardDto.setCreation(creationTime);
                cardRepository.save(cardDto);
            }
            for (Card card : player.getDeck()) {
                CardDto cardDto = cardToDto(card);
                cardDto.setReserve(false);
                cardDto.setPlayerDto(playerDto);
                cardDto.setBoard(boardDto);
                cardDto.setCreation(creationTime);
                cardRepository.save(cardDto);
            }
            for (Aristocrat aristocrat: player.getAristocrats()){
                AristocratDto aristocratDto = convertAristocratToDto(aristocrat);
                aristocratDto.setBoardDto(boardDto);
                aristocratDto.setPlayerDto(playerDto);
                aristocratDto.setCreation(creationTime);
            }
        }
        for (Tier tier : Tier.values())
            if (!tier.equals(Tier.RESERVE)) {
                for (Card card : board.getTradeRow().getCardsHidden().get(tier)) {
                    CardDto cardDto = cardToDto(card);
                    cardDto.setBoard(boardDto);
                    cardDto.setVisible(false);
                    cardDto.setCreation(creationTime);
                    cardRepository.save(cardDto);
                }
                for (Card card : board.getTradeRow().getCardsVisible().get(tier)) {
                    CardDto cardDto = cardToDto(card);
                    cardDto.setBoard(boardDto);
                    cardDto.setVisible(true);
                    cardDto.setCreation(creationTime);
                    cardRepository.save(cardDto);
                }
            }
        for (Aristocrat aristocrat : board.getAristocrats()) {
            AristocratDto aristocratDto = convertAristocratToDto(aristocrat);
            aristocratDto.setBoardDto(boardDto);
            aristocratDto.setCreation(creationTime);
            aristocratRepository.save(aristocratDto);
        }
        return boardDto.getId();
    }

    private CardDto cardToDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setBlue(card.getCost().get(Gem.BLUE));
        cardDto.setGreen(card.getCost().get(Gem.GREEN));
        cardDto.setRed(card.getCost().get(Gem.RED));
        cardDto.setBrown(card.getCost().get(Gem.BROWN));
        cardDto.setWhite(card.getCost().get(Gem.WHITE));
        cardDto.setPicture(card.getPicture());
        cardDto.setPoints(card.getPoints());
        cardDto.setTier(card.getTier());
        cardDto.setProduction(card.getProduction());
        return cardDto;
    }

    private Card dtoToCard(CardDto cardDto) {
        return new Card(cardDto.getTier(), cardDto.getRed(), cardDto.getGreen(), cardDto.getBlue(), cardDto.getBrown(), cardDto.getWhite(), cardDto.getProduction(), cardDto.getPoints(), cardDto.getPicture());
    }

    private AristocratDto convertAristocratToDto(Aristocrat aristocrat){
        AristocratDto aristocratDto = new AristocratDto();
        aristocratDto.setBlue(aristocrat.getCost().get(Gem.BLUE));
        aristocratDto.setBrown(aristocrat.getCost().get(Gem.BROWN));
        aristocratDto.setRed(aristocrat.getCost().get(Gem.RED));
        aristocratDto.setGreen(aristocrat.getCost().get(Gem.GREEN));
        aristocratDto.setWhite(aristocrat.getCost().get(Gem.WHITE));
        aristocratDto.setImage(aristocrat.getImage());
        return aristocratDto;
    }
}
