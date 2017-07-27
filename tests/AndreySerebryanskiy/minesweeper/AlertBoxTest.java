package AndreySerebryanskiy.minesweeper;

import AndreySerebryanskiy.minesweeper.AlertBox.AlertBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

/**
 * Created by andreyserebryanskiy on 25/07/2017.
 */
public class AlertBoxTest extends ApplicationTest{
    private Battlefield field;
    private Tile[][] tiles;
    private Game testGame;

    @After
    public void tearDown() throws Exception {
        FxToolkit.cleanupStages();
        testGame = new Game(100,100, true);
        field = testGame.getField();
        tiles = field.getTiles();
    }

    @Test
    public void ifGameIsWonAppropriatePopUpIsShown() throws Exception {
        Game.open(tiles[2][2]);
        clickOn(tiles[2][0]);

        verifyThat("#alertBox", isVisible());
        assertThat(AlertBox.getTitle(), is("Bravo!"));
    }

    @Test
    public void ifGameIsLostAppropriatePopUpIsShown() throws Exception {
        clickOn(tiles[0][0]);

        verifyThat("#alertBox", isVisible());
        assertThat(AlertBox.getTitle(), is("Game over"));
    }

    @Test
    public void ifGameIsLostAllTilesAreOpened() throws Exception {
        clickOn(tiles[0][0]);

        verifyThat(tiles[2][2], isVisible());
    }

    @Override
    public void start(Stage stage) throws Exception {
        testGame = new Game(100,100, true);
        field = testGame.getField();
        tiles = field.getTiles();
        stage.setScene(new Scene(field));
        stage.show();
    }
}
