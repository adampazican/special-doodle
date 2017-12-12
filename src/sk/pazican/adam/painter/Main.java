package sk.pazican.adam.painter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Hlavná trieda programu, ktorá sa stará o inicializáciu scény, plátna a nastavení štetca
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public class Main extends Application {

    /**
     * Metóda inicializujúca scénu, plátno, nastavenia štetca
     * @param primaryStage primárne okno aplikácie
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane layout = new BorderPane();

        /* Centrovanie plátna */
        HBox container = new HBox();
        container.getChildren().add(new PaintingArea());
        container.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.getChildren().add(container);
        vbox.setAlignment(Pos.CENTER);

        layout.setCenter(vbox);
        /**/

        layout.setTop(new ToolSettings());

        Scene scene = new Scene(layout, 800, 560, true, SceneAntialiasing.DISABLED);
        primaryStage.setTitle("Painter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Hlavná metóda programu, inicializuje javaFX
     * @param args argumenty programu
     */
    public static void main(String[] args) {
        launch(args);
    }
}
