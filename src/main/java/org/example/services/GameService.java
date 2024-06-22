package org.example.services;

import org.example.entity.Game;
import org.example.interfaces.Repository;

import java.time.LocalDate;
import java.util.List;

public class GameService {
    private Repository repository;

    public GameService(Repository inputRepository) {
        repository = inputRepository;
    }

    public void addGame(String name, String releaseDate, double rating, double cost, String description) {
        Game game = new Game();
        game.setName(name);
        game.setReleaseDate(LocalDate.parse(releaseDate));
        game.setRating(rating);
        game.setCost(cost);
        game.setDescription(description);
        game.setCreationDate(LocalDate.now());
        repository.saveGame(game);
    }

    public void removeGame(Long id) {
        repository.deleteGame(id);
    }

    public Game findGameByName(String name) {
        return repository.getGameByName(name);
    }

    public List<Game> filterGamesByPrice(double minPrice, double maxPrice) {
        return repository.filterGamesByPrice(minPrice, maxPrice);
    }

    public List<Game> listAllGamesSortedByCreationDate() {
        return repository.getAllGamesSortedByCreationDate();
    }

    public List<Game> listAllGames() {
        return repository.getAllGames();
    }
}

