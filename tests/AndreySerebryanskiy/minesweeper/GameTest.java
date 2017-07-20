package AndreySerebryanskiy.minesweeper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by andreyserebryanskiy on 19/07/2017.
 */
public class GameTest implements BattlefieldInterface  {
    private int bombsCounter;
    private Tile[][] tiles;
    private Game game;

    @Override
    public void createField(int xTiles, int yTiles) {
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
    }

    @Before
    public void setUp() throws Exception {
        Battlefield field = new Battlefield(2,2);
        tiles[0][0].hasBomb = true;

        game = new Game();
    }

    @Test
    public void gameCouldBeWon() throws Exception {
        game.open(tiles[1][0]);
        game.open(tiles[0][1]);
        game.open(tiles[1][1]);

        assertThat(game.isWon, is(true));
    }
}