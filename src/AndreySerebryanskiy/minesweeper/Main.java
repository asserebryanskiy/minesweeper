package AndreySerebryanskiy.minesweeper;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));

        window.setOnCloseRequest(e -> Platform.exit());
        window.getIcons().add(new Image("/images/icon32.png"));
        window.setResizable(false);
        window.setTitle("Minesweeper");
        window.setScene(new Scene(root));
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getWindow() {
        return window;
    }
}
