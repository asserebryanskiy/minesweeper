package AndreySerebryanskiy.minesweeper;


import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by andreyserebryanskiy on 14/07/2017.
 */
public class Tile extends StackPane {

    private static final int TILE_SIZE = 20;

    private int x;
    private int y;

    protected boolean hasBomb;
    protected boolean isOpened;
    protected boolean isMarked;

    private SVGPath bomb;
    private Rectangle background;
    private SVGPath flag;

    protected int bombs;
    protected Text value;


    public Tile(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        bomb = new SVGPath();
        bomb.setContent("M892.735,892.917c-0.067-0.167-0.181-0.285-0.341-0.352c-0.161-0.067-0.325-0.067-0.492,0 c-0.723,0.294-1.363,0.72-1.918,1.275c-0.556,0.556-0.981,1.195-1.276,1.918c-0.067,0.167-0.067,0.332,0,0.492 s0.184,0.274,0.352,0.342c0.087,0.033,0.167,0.05,0.241,0.05c0.281,0,0.482-0.134,0.603-0.401c0.228-0.562,0.558-1.06,0.989-1.492 c0.432-0.432,0.929-0.762,1.492-0.989c0.167-0.074,0.285-0.191,0.352-0.352C892.802,893.248,892.802,893.083,892.735,892.917z M902.197,889.34l0.462,0.462l-2.45,2.441l0.683,0.683c0.127,0.127,0.191,0.28,0.191,0.457c0,0.178-0.064,0.33-0.191,0.457 l-0.643,0.643c0.596,1.078,0.894,2.227,0.894,3.445c0,0.957-0.186,1.873-0.558,2.747c-0.371,0.874-0.873,1.627-1.506,2.26 s-1.387,1.136-2.261,1.507c-0.874,0.372-1.79,0.558-2.747,0.558s-1.873-0.186-2.747-0.558c-0.874-0.371-1.627-0.874-2.26-1.507 s-1.135-1.386-1.506-2.26s-0.558-1.79-0.558-2.747c0-0.958,0.186-1.874,0.558-2.748c0.372-0.874,0.874-1.627,1.506-2.26 s1.386-1.135,2.26-1.507s1.79-0.558,2.747-0.558c1.219,0,2.367,0.298,3.445,0.894l0.643-0.643c0.128-0.127,0.28-0.191,0.457-0.191 c0.178,0,0.33,0.063,0.457,0.191l0.684,0.683L902.197,889.34z M902.278,888.778c-0.067,0.067-0.141,0.101-0.222,0.101 c-0.087,0-0.164-0.034-0.23-0.101l-0.914-0.904c-0.061-0.067-0.091-0.144-0.091-0.231c0-0.087,0.03-0.164,0.091-0.231 c0.066-0.06,0.144-0.09,0.23-0.09s0.164,0.03,0.231,0.09l0.904,0.914c0.066,0.061,0.1,0.136,0.1,0.226 S902.345,888.718,902.278,888.778z M904.588,891.088c-0.073,0.06-0.15,0.09-0.23,0.09c-0.081,0-0.157-0.03-0.231-0.09l-0.904-0.914 c-0.066-0.061-0.1-0.136-0.1-0.226s0.033-0.166,0.1-0.226c0.061-0.067,0.136-0.101,0.227-0.101c0.09,0,0.166,0.034,0.226,0.101 l0.914,0.904c0.061,0.067,0.091,0.144,0.091,0.231C904.679,890.944,904.648,891.021,904.588,891.088z M905,889.25 c0,0.094-0.03,0.171-0.091,0.231c-0.06,0.061-0.137,0.09-0.23,0.09h-0.965c-0.094,0-0.17-0.03-0.23-0.09 c-0.061-0.06-0.091-0.137-0.091-0.231s0.03-0.171,0.091-0.231c0.061-0.061,0.137-0.09,0.23-0.09h0.965c0.094,0,0.171,0.03,0.23,0.09 C904.97,889.079,905,889.156,905,889.25z M903.071,887.321v0.964c0,0.094-0.03,0.171-0.09,0.231c-0.061,0.061-0.138,0.09-0.231,0.09 s-0.171-0.03-0.231-0.09c-0.06-0.06-0.09-0.137-0.09-0.231v-0.964c0-0.094,0.03-0.17,0.09-0.231c0.061-0.06,0.138-0.09,0.231-0.09 s0.171,0.03,0.231,0.09C903.041,887.151,903.071,887.228,903.071,887.321z M904.588,887.874l-0.914,0.904 c-0.066,0.067-0.141,0.101-0.221,0.101c-0.087,0-0.164-0.034-0.231-0.101c-0.066-0.06-0.1-0.136-0.1-0.226s0.033-0.166,0.1-0.226 l0.904-0.914c0.067-0.06,0.145-0.09,0.231-0.09s0.164,0.03,0.23,0.09c0.061,0.067,0.091,0.144,0.091,0.231 C904.679,887.73,904.648,887.807,904.588,887.874z");
        bomb.setScaleX(0.8);
        bomb.setScaleY(0.8);
        bomb.setVisible(false);

        value = new Text();
        value.setFont(Font.font(16));

        background = new Rectangle(TILE_SIZE-2,TILE_SIZE-2);
        background.setStroke(Color.LIGHTGRAY);
        background.setFill(Color.web("#181818"));

        flag = new SVGPath();
        flag.setContent("M6,21H4.5V3H6V21z M19.5,4.959c0,0-1.216,1.073-2.815,1.073c-2.52,0-2.577-2.171-5.503-2.171 c-1.581,0-3.056,0.735-3.682,1.271v9.064c0.888-0.615,2.234-1.261,3.692-1.261c2.763,0,3.15,2.065,5.613,2.065 c1.591,0,2.694-1.02,2.694-1.02V4.959z");
        flag.setFill(Color.WHITE);
        flag.setScaleX(0.8);
        flag.setScaleY(0.8);
        flag.setVisible(false);

        getChildren().addAll(bomb, value, background, flag);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                game.mark(this);
            } else {
                game.open(this);
            }
        });
    }



    public void setOpened(boolean booleanValue) {
        isOpened = true;
        background.setVisible(false);
        if(hasBomb){
            bomb.setVisible(true);
            value.setVisible(false);
        }
    }

    public void setMarked(boolean value) {
        isMarked = value;
        flag.setVisible(value);
    }

    public static int getTILE_SIZE() {
        return TILE_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}