package AndreySerebryanskiy.minesweeper;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by andreyserebryanskiy on 24/07/2017.
 */
public class GameTest {
    private Battlefield field;
    private Tile[][] tiles;

    @Before
    public void setUp() throws Exception {
        // field size has no meaning, because test field is always of 3x3 tile size,
        // with bombs at [0][0] and [1][0]. It could be changed in Battlefield class.
        Game testGame = new Game(100,100, true);
        field = testGame.getField();
        tiles = field.getTiles();
    }


    @Test
    public void nonBombTileWithValueOpensProperly() throws Exception {
        //Act
        Game.open(tiles[0][1]);

        //Assert
        assertThat(tiles[0][1].isOpened, is(true));
        assertThat(tiles[0][1].value.getText(), is("2"));
    }

    @Test
    public void openingTileWithZeroBombsAroundOpensNearbyTiles() throws Exception {
        //Act
        Game.open(tiles[2][2]);

        //Assert
        assertThat(tiles[1][2].isOpened, is(true));
    }

    @Test
    public void markedTileCouldNotBeenOpened() throws Exception {
        //Act
        Game.mark(tiles[0][0]);
        Game.open(tiles[0][0]);

        //Assert
        assertThat(tiles[0][0].isOpened, is(false));
    }

    @Test
    public void markingTileDecrementsBombsCounter() throws Exception {
        //Act
        Game.mark(tiles[0][0]);

        //Assert
        assertThat(field.bombsRemaining.getText(), is("1"));
    }
}