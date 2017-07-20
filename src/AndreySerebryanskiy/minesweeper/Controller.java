package AndreySerebryanskiy.minesweeper;

import javafx.event.ActionEvent;
import javafx.scene.Scene;

/**
 * Created by andreyserebryanskiy on 18/07/2017.
 */
public class Controller {

    private static Battlefield field;

    public void handleSetSmallScene(ActionEvent actionEvent) {
        field = new Battlefield(200, 200);
        Scene smallScene = new Scene(field);
        Main.getWindow().setScene(smallScene);
    }

    public void handleSetMiddleScene(ActionEvent actionEvent) {
        field = new Battlefield(400, 300);
        Scene mediumScene = new Scene(field);
        Main.getWindow().setScene(mediumScene);
    }

    public void handleSetLargeScene(ActionEvent actionEvent) {
        field = new Battlefield(600, 400);
        Scene largeScene = new Scene(field);
        Main.getWindow().setScene(largeScene);
    }

    public static Battlefield getField() {
        return field;
    }
}
