package org.example;

import org.example.entity.Game;
import org.example.repository.dao.GameRepository;
import org.example.services.GameService;

import java.util.List;
import java.util.Scanner;

public class GameStoreConsole {
    private GameService gameService = new GameService(new GameRepository());
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println(
                    """
                            Виберіть дію:
                            1. Додати гру
                            2. Видалити гру
                            3. Пошук гри за назвою
                            4. Фільтрувати ігри за ціною
                            5. Показати всі ігри, відсортовані за датою додавання
                            6. Перегляд списку всіх доступних ігор
                            7. Вийти"""
            );

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addGame();
                    break;
                case 2:
                    deleteGame();
                    break;
                case 3:
                    searchGameByName();
                    break;
                case 4:
                    filterGamesByPrice();
                    break;
                case 5:
                    listAllGamesSortedByCreationDate();
                    break;
                case 6:
                    listAllGames();
                    break;
                case 7:
                    System.exit(0);
            }
        }
    }

    public void addGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter game name:");
        String name = scanner.nextLine();

        System.out.println("Enter release date (yyyy-MM-dd):");
        String releaseDate = scanner.nextLine();

        System.out.println("Enter rating:");
        double rating = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter cost:");
        double cost = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        gameService.addGame(name, releaseDate, rating, cost, description);
        System.out.println("Game added successfully!");
    }

    private void deleteGame() {
        System.out.println("Введіть ID гри, яку бажаєте видалити:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        gameService.removeGame(id);
        System.out.println("Гру видалено успішно.");
    }

    private void searchGameByName() {
        System.out.println("Введіть назву гри:");
        String name = scanner.nextLine();
        Game game = gameService.findGameByName(name);
        if (game != null) {
            System.out.println(game);
        } else {
            System.out.println("Гру не знайдено.");
        }
    }

    private void filterGamesByPrice() {
        System.out.println("Введіть мінімальну ціну:");
        double minPrice = scanner.nextDouble();
        System.out.println("Введіть максимальну ціну:");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();

        List<Game> games = gameService.filterGamesByPrice(minPrice, maxPrice);
        games.forEach(System.out::println);
    }

    private void listAllGamesSortedByCreationDate() {
        List<Game> games = gameService.listAllGamesSortedByCreationDate();
        games.forEach(System.out::println);
    }

    private void listAllGames() {
        List<Game> games = gameService.listAllGames();
        games.forEach(System.out::println);
    }

    public static void main(String[] args) {
        GameStoreConsole console = new GameStoreConsole();
        console.start();
    }
}

