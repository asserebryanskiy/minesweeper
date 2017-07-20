package AndreySerebryanskiy.minesweeper;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Game {

    private Battlefield field = Controller.getField();
    private int numberOfBombs = field.getBombsCounter();
    private int numberOfTiles = field.getxTiles() * field.getyTiles();
    private int bombsRemainingInt = Integer.parseInt(field.bombsRemaining.getText());
    private int openedTiles = 0;
    public boolean isWon = false;

    public void open(Tile tile) {
        if (tile.isOpened || tile.isMarked) {
            return;
        }

        if (tile.hasBomb) {
            loseGame();
        }

        tile.setOpened(true);
        openedTiles++;
        System.out.println(openedTiles);

        if (tile.bombs == 0) {
            field.getNeighbours(tile).forEach(t -> open(t));
        }

        if(openedTiles == numberOfTiles - numberOfBombs) {
            isWon = true;
            System.out.println(isWon);
        }
    }

    public void mark(Tile tile) {
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

    private void changeBombsRemaining() {
        field.bombsRemaining.setText(String.valueOf(bombsRemainingInt));
    }

    private boolean winGame() {
        int counter = 0;
        for(int y = 0; y< Controller.getField().getyTiles(); y++) {
            for (int x = 0; x < Controller.getField().getxTiles(); x++) {
                Tile tile = Controller.getField().getTiles()[x][y];
                if(tile.isOpened) counter++;
            }
        }

        if (counter == field.getBombsCounter()) return true;
        return false;
    }

    private void loseGame() {
        for(int y = 0; y< Controller.getField().getyTiles(); y++) {
            for (int x = 0; x < Controller.getField().getxTiles(); x++) {
                Tile tile = Controller.getField().getTiles()[x][y];
                if(!tile.isMarked) {
                    tile.setOpened(true);
                }
            }
        }

        Stage popup = new Stage();
        Label label = new Label("Bad luck! Whanna try one more time?");
        Button btn = new Button("Try again");
        btn.setOnMouseClicked(e -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Main.getWindow().setScene(new Scene(root));
            popup.close();
        });
        
        Rectangle back = new Rectangle(250, 80, Color.LIGHTGRAY);
        back.setStroke(Color.LIGHTGRAY);
        back.setStrokeWidth(5);
        back.setStrokeLineJoin(StrokeLineJoin.ROUND);

        StackPane stackPane = new StackPane();
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(label, btn);
        stackPane.getChildren().addAll(back, vbox);
        popup.setScene(new Scene(stackPane));
        popup.setTitle("Game over");
        popup.show();
    }
}
