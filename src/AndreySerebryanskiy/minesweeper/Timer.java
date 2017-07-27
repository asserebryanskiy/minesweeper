package AndreySerebryanskiy.minesweeper;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.text.DecimalFormat;

/**
 * Created by andreyserebryanskiy on 26/07/2017.
 */
public class Timer extends Pane{
    private static Label currentTime = new Label();
    private int seconds = 0;
    private static Timeline timer;

    public Timer() {
        getChildren().add(currentTime);
        timer = new Timeline();
        timer.setCycleCount(Animation.INDEFINITE);
        timer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            seconds++;
            changeCurrentTime();
        }));
        timer.play();
    }

    private void changeCurrentTime() {
        DecimalFormat df = new DecimalFormat("00");
        currentTime.setText(df.format(seconds));
    }

    public static String getCurrentTime() {
        return currentTime.getText();
    }

    public static void stop() {
        timer.stop();
    }
}
