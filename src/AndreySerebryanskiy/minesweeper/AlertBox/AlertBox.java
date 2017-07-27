package AndreySerebryanskiy.minesweeper.AlertBox;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by andreyserebryanskiy on 20/07/2017.
 */
public class AlertBox {
    private static String title;
    protected static String message;
    public static Stage stage;

    public AlertBox(String title, String message){
        this.title = title;
        this.message = message;
    }

    public void display() {
        stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/AlertBox.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }

    public static String getTitle() {
        return title;
    }
}
