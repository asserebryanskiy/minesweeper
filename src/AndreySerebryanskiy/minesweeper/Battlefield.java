package AndreySerebryanskiy.minesweeper;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreyserebryanskiy on 14/07/2017.
 */

public class Battlefield extends GridPane {
    private int xTiles;
    private int yTiles;

    protected Text bombsRemaining;
    private int bombsCounter;

    private Tile[][] tiles;

    public Battlefield(int width, int height, boolean forTest) throws InvalidFieldSizeException{
        //checks if all elements will fit the field size
        if (width % Tile.getTILE_SIZE() != 0
                || height % Tile.getTILE_SIZE() != 0) {
            throw new InvalidFieldSizeException("Width and height should be a multiple of "
                                                + String.valueOf(Tile.getTILE_SIZE()));
        } else if (width < 80) {
            throw new InvalidFieldSizeException("Minimal width for the field is "
                                                + String.valueOf(4*Tile.getTILE_SIZE()));
        }

        // creates a matrix of tiles, counts bombsAround for each Tile
        if(forTest) {
            createTestTileMatrix();
        } else {
            createRandomTileMatrix(width, height);
        }
        // adds bombs counter on the field in the right upper corner
        addBombsCounter();
        addTimer();
        //generates field
        generateField();
    }

    private void createRandomTileMatrix(int width, int height) {
        xTiles = width / Tile.getTILE_SIZE();
        yTiles = height / Tile.getTILE_SIZE();

        tiles = new Tile[xTiles][yTiles];
        bombsCounter = 0;

        for(int y = 0; y<yTiles; y++) {
            for(int x = 0; x<xTiles; x++) {
                Tile tile = new Tile(x, y,Math.random() < 0.05);

                if(tile.hasBomb) bombsCounter++;

                tiles[x][y] = tile;
            }
        }
    }

    private void createTestTileMatrix() {
        xTiles = 3;
        yTiles = 3;
        bombsCounter = 2;

        tiles = new Tile[xTiles][yTiles];

        for(int y = 0; y<yTiles; y++) {
            for(int x = 0; x<xTiles; x++) {
                Tile tile = new Tile(x, y,false);

                tiles[x][y] = tile;
            }
        }
        tiles[0][0].hasBomb = true;
        tiles[1][0].hasBomb = true;
    }

    private void addTimer() {
        Timer timer = new Timer();
        add(timer, 0, 0);
    }

    private void addBombsCounter() {
        bombsRemaining = new Text(String.valueOf(bombsCounter));
        add(bombsRemaining, xTiles-2, 0, 2, 1);
    }

    private void generateField() {
        for(int y = 0; y < yTiles; y++) {
            for(int x = 0; x < xTiles; x++) {
                Tile tile = tiles[x][y];
                tile.setId("Tile" + String.valueOf(x) + "." + String.valueOf(y));
                tile.bombsAround = (int)(getNeighbours(tile).stream().filter(t -> t.hasBomb).count());
                if (tile.bombsAround >0 && !tile.hasBomb) {
                    tile.value.setText(String.valueOf(tile.bombsAround));
                }

                add(tile, x, y+1);
                setHgrow(tile, Priority.ALWAYS);
                setVgrow(tile, Priority.ALWAYS);
            }
        }
    }

    public List<Tile> getNeighbours(Tile tile) {
        List<Tile> neighbours = new ArrayList<>();

        int x = tile.getxCoordinate();
        int y = tile.getyCoordinate();

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
