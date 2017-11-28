package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane layout = new BorderPane();



        HBox container = new HBox();
        container.getChildren().add(new PaintingArea());
        container.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.getChildren().add(container);
        vbox.setAlignment(Pos.CENTER);

        layout.setCenter(vbox);
        layout.setTop(new ToolSettings());

        Scene scene = new Scene(layout, 800, 560, true, SceneAntialiasing.DISABLED);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
