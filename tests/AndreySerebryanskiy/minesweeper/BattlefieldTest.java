package AndreySerebryanskiy.minesweeper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by andreyserebryanskiy on 22/07/2017.
 */
public class BattlefieldTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void battlefieldWithWidthLessThanMinIsNotAllowed() throws Exception {
        int cellSize = Tile.getTILE_SIZE();
        thrown.expect(InvalidFieldSizeException.class);
        thrown.expectMessage("Minimal width for the field is "
                            + String.valueOf(4*cellSize));

        Battlefield field = new Battlefield(60,100, false);
    }

    @Test(expected = InvalidFieldSizeException.class)
    public void widthNotAMultipleOfTileSizeIsNotAllowed() throws Exception {
        Battlefield field = new Battlefield(81, 100, false);
    }

    @Test(expected = InvalidFieldSizeException.class)
    public void heightNotAMultipleOfTileSizeIsNotAllowed() throws Exception {
        Battlefield field = new Battlefield(80, 101, false);
    }
}