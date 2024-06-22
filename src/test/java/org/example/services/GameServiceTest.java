package org.example.services;

import org.example.entity.Game;
import org.example.repository.GameRepositoryMock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameServiceTest {
    private final GameService gameService = new GameService(new GameRepositoryMock(new ArrayList<>()));

    @Test
    public void testAddGame() {
        gameService.addGame("Test Game", String.valueOf(LocalDate.now()), 4.5, 59.99, "Test Description");
        Game game = gameService.findGameByName("Test Game");
        assertNotNull(game);
        assertEquals("Test Game", game.getName());
    }

    @Test
    public void testRemoveGame() {
        gameService.addGame("Test Game", String.valueOf(LocalDate.now()), 4.5, 59.99, "Test Description");
        Game game = gameService.findGameByName("Test Game");
        assertNotNull(game);
        gameService.removeGame(game.getId());
        List<Game> games = gameService.listAllGames();
        assertEquals(0, games.size());
    }

    @Test
    public void testFilterGamesByPrice() {
        gameService.addGame("Cheap Game", String.valueOf(LocalDate.now()), 4.0, 10.99, "Cheap Game Description");
        gameService.addGame("Expensive Game", String.valueOf(LocalDate.now()), 4.5, 99.99, "Expensive Game Description");

        List<Game> games = gameService.filterGamesByPrice(5.00, 20.00);
        assertEquals(1, games.size());
        assertEquals("Cheap Game", games.get(0).getName());
    }

    @Test
    public void testListAllGamesSortedByCreationDate() {
        gameService.addGame("First Game", String.valueOf(LocalDate.now()), 4.5, 59.99, "First Description");
        gameService.addGame("Second Game", String.valueOf(LocalDate.now()), 4.0, 39.99, "Second Description");

        List<Game> games = gameService.listAllGamesSortedByCreationDate();
        assertEquals(2, games.size());
        assertEquals("First Game", games.get(0).getName());
    }

    @Test
    public void testListAllGames() {
        gameService.addGame("Game1", String.valueOf(LocalDate.now()), 4.0, 49.99, "Description1");
        gameService.addGame("Game2", String.valueOf(LocalDate.now()), 4.5, 59.99, "Description2");

        List<Game> games = gameService.listAllGames();
        assertEquals(2, games.size());
    }
}

