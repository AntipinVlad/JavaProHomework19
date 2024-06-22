package org.example.interfaces;

import org.example.entity.Game;

import java.util.List;

public interface Repository {
    void saveGame(Game game);
    void deleteGame(Long id);
    Game getGameByName(String name);
    List<Game> filterGamesByPrice(double minPrice, double maxPrice);
    List<Game> getAllGamesSortedByCreationDate();
    List<Game> getAllGames();
}
