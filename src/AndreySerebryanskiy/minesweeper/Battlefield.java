package AndreySerebryanskiy.minesweeper;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreyserebryanskiy on 14/07/2017.
 */

public class Battlefield extends GridPane implements BattlefieldInterface {
    private int xTiles;
    private int yTiles;

    protected Text bombsRemaining;
    private int bombsCounter;

    private Tile[][] tiles;

    public Battlefield(int width, int height) {
        createField(width, height);
    }

    @Override
    public void createField(int width, int height) {
        xTiles = width / Tile.getTILE_SIZE();
        yTiles = height / Tile.getTILE_SIZE();

        tiles = new Tile[xTiles][yTiles];
        bombsCounter = 0;

        // creates an array of tiles, counts bombs
        for(int y = 0; y<yTiles; y++) {
            for(int x = 0; x<xTiles; x++) {
                Tile tile = new Tile(x, y,Math.random() < 0.05);

                if(tile.hasBomb) bombsCounter++;

                tiles[x][y] = tile;
            }
        }

        bombsRemaining = new Text(String.valueOf(bombsCounter));
        Text timer = new Text("100");
        add(timer, 0, 0, 2, 1);
        add(bombsRemaining, xTiles-3, 0, 2, 1);

        // fills cells with bomb counts
        for(int y = 0; y < yTiles; y++) {
            for(int x = 0; x < xTiles; x++) {
                Tile tile = tiles[x][y];
                tile.bombs = (int)(getNeighbours(tile).stream().filter(t -> t.hasBomb).count());
                if (tile.bombs>0 && !tile.hasBomb) {
                    tile.value.setText(String.valueOf(tile.bombs));
                }

                add(tile, x, y+1);
                setHgrow(tile, Priority.ALWAYS);
                setVgrow(tile, Priority.ALWAYS);
            }
        }
    }

    public List<Tile> getNeighbours(Tile tile) {
        List<Tile> neighbours = new ArrayList<>();

        int x = tile.getX();
        int y = tile.getY();

        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++) {

                int newX = x + i;
                int newY = y + j;

                if (i == 0 && j == 0 || newX < 0 || newY < 0 || newX >= xTiles || newY >= yTiles) {
                    continue;
                }
                neighbours.add(tiles[newX][newY]);
            }

        }
        return neighbours;
    }

    public int getBombsCounter() {
        return bombsCounter;
    }

    public int getxTiles() {
        return xTiles;
    }

    public int getyTiles() {
        return yTiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }


}
