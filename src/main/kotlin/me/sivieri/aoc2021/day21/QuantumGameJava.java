package me.sivieri.aoc2021.day21;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class QuantumGameJava {
    private Map<QuantumGameStatus, BigDecimal> games;
    private final Map<Integer, Integer> diceValues = Map.of(
            3, 1,
            4, 3,
            5, 6,
            6, 7,
            7, 6,
            8, 3,
            9, 1
    );

    public QuantumGameJava(int startingPositionPlayer1, int startingPositionPlayer2) {
        var game = new QuantumGameStatus(
            new QuantumDicePlayer(startingPositionPlayer1, 0),
            new QuantumDicePlayer(startingPositionPlayer2, 0),
            0
        );
        this.games = new HashMap<>(Map.of(game, BigDecimal.valueOf(1L)));
    }

    public void play() {
        while (games.keySet().stream().anyMatch(game -> !game.isWon())) {
            long count = games.keySet().stream().filter(game -> !game.isWon()).count();
            System.out.println("Cache: " + games.size());
            System.out.println("Not won yet: " + count);
            var newGames = new HashMap<QuantumGameStatus, BigDecimal>();
            for (QuantumGameStatus game : games.keySet()) {
                playGame(game, newGames);
            }
            games = newGames;
        }
    }

    private void playGame(QuantumGameStatus game, Map<QuantumGameStatus, BigDecimal> newGames) {
        var currentCount = games.get(game);

        if (game.isWon()) {
            newGames.merge(game, currentCount, BigDecimal::add);
            return;
        }

        diceValues.forEach((dice, count) -> newGames.merge(game.play(dice), currentCount.multiply(BigDecimal.valueOf(count)), BigDecimal::add));
    }

    public long wonGamesForPlayer1() {
        return games.entrySet().stream().filter(entry -> entry.getKey().getPlayer1().getScore() >= 21).mapToLong(entry -> entry.getValue().longValue()).sum();
    }

    public long wonGamesForPlayer2() {
        return games.entrySet().stream().filter(entry -> entry.getKey().getPlayer2().getScore() >= 21).mapToLong(entry -> entry.getValue().longValue()).sum();
    }

}
