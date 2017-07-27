package AndreySerebryanskiy.minesweeper.AlertBox;

import AndreySerebryanskiy.minesweeper.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by andreyserebryanskiy on 20/07/2017.
 */
public class AlertBoxController implements Initializable {

    @FXML
    private Label alertLabel;

    public void handleLoadNewGame(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Main.getWindow().setScene(new Scene(root));
        AlertBox.stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alertLabel.setText(AlertBox.message);
    }

    public void handleCloseApp(ActionEvent actionEvent) {
        Platform.exit();
    }
}
