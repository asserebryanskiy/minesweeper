package AndreySerebryanskiy.minesweeper;

/**
 * Created by andreyserebryanskiy on 18/07/2017.
 */
public class MainController {

    public void handleSetSmallScene() throws InvalidFieldSizeException {
        new Game(200,200, false);
    }

    public void handleSetMiddleScene() throws InvalidFieldSizeException {
        new Game(400,300, false);
    }

    public void handleSetLargeScene() throws InvalidFieldSizeException {
        new Game(600,400, false);
    }
}
