package AndreySerebryanskiy.minesweeper;

import AndreySerebryanskiy.minesweeper.AlertBox.AlertBox;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;

public class Game {
    private static boolean gameIsWon = false;
    private static int openedTiles;

    private static Battlefield field;
    private static int numberOfBombs;
    private static int numberOfTiles;
    private static int bombsRemainingInt;
    private static AudioClip applause;

    public Game(int width, int height, boolean forTest) throws InvalidFieldSizeException {
        field = new Battlefield(width, height, forTest);
        numberOfBombs = field.getBombsCounter();
        numberOfTiles = field.getxTiles() * field.getyTiles();
        bombsRemainingInt = Integer.parseInt(field.bombsRemaining.getText());
        openedTiles = 0;
        applause = new AudioClip(getClass().getResource("/sounds/applause.mp3").toExternalForm());

        if(!forTest) {
            Main.getWindow().setScene(new Scene(field));
        }
    }

    public static void open(Tile tile)  {
        if (tile.isOpened || tile.isMarked) {
            return;
        }

        if (tile.hasBomb) {
            tile.setExploded();
            loseGame();
            return;
        }

        tile.setOpened();
        openedTiles++;

        if (tile.bombsAround == 0) {
            field.getNeighbours(tile).forEach(t -> open(t));
        }

        if(openedTiles == numberOfTiles - numberOfBombs
                && !gameIsWon) {
            gameIsWon = true;
            winGame();
        }
    }

    public static void mark(Tile tile) {
        if (tile.isOpened) {
            return;
        } else if (tile.isMarked) {
            tile.setMarked(false);
            bombsRemainingInt++;
            changeBombsRemaining();
            return;
        }

        tile.setMarked(true);
        bombsRemainingInt--;
        changeBombsRemaining();
    }

    private static void changeBombsRemaining() {
        field.bombsRemaining.setText(String.valueOf(bombsRemainingInt));
    }

    private static void winGame() {
        Timer.stop();
        applause.play();
        AlertBox congratulationsWindow = new AlertBox("Bravo!", "Well done! "
                                                                    + System.lineSeparator()
                                                                    + "You've finished in " + Timer.getCurrentTime() + " seconds"
                                                                    + System.lineSeparator()
                                                                    + System.lineSeparator()
                                                                    + "Whanna play one more time?");
        congratulationsWindow.display();
    }

    private static void loseGame() {
        openAllTiles();
        Timer.stop();
        AlertBox gameOverWindow = new AlertBox("Game over", "Bad luck! "
                                                                + System.lineSeparator() +
                                                                "Whanna try one more time?");
        gameOverWindow.display();
    }

    private static void openAllTiles() {
        for(int y = 0; y< field.getyTiles(); y++) {
            for (int x = 0; x < field.getxTiles(); x++) {
                Tile tile = field.getTiles()[x][y];
                if(!tile.isMarked) {
                    tile.setOpened();
                }
            }
        }
    }

    protected Battlefield getField() {
        return field;
    }
}
