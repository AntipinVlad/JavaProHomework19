package org.example.repository;

import org.example.entity.Game;
import org.example.repository.dao.GameRepository;

import java.util.Comparator;
import java.util.stream.Collectors;

import java.util.List;

public class GameRepositoryMock extends GameRepository {
    private final List<Game> games;

    public GameRepositoryMock(List<Game> games) {
        this.games = games;
    }

    @Override
    public void saveGame(Game game) {
        if (games.isEmpty()) {
            game.setId(0L);
        } else {
            game.setId(games.getLast().getId() + 1);
        }
        games.add(game);
    }

    @Override
    public void deleteGame(Long id) {
        games.remove(Math.toIntExact(id));
    }

    @Override
    public Game getGameByName(String name) {
        return games.stream().filter(g -> g.getName().equals(name)).findAny().orElseThrow();
    }

    @Override
    public List<Game> filterGamesByPrice(double minPrice, double maxPrice) {
        return games.stream().filter(g -> g.getCost() >= minPrice && g.getCost() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> getAllGamesSortedByCreationDate() {
        games.sort(Comparator.comparing(Game::getCreationDate));
        return games;
    }

    @Override
    public List<Game> getAllGames() {
        return games;
    }
}
