package sample;

import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class ToolSettings extends HBox implements Observer{
    private Store store;

    public ToolSettings(){
        this.setPadding(new Insets(10, 5, 10, 5));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #222D32;");

        this.store = Store.getInstance();
        this.store.addObserver(this);

        renderBrushSettings(this.store.getLineWidth());
    }

    public void renderBrushSettings(double brushSize){
        this.getChildren().removeAll();

        Label brushSizeLabel = new Label();
        brushSizeLabel.setPadding(new Insets(5,0,0,5));
        brushSizeLabel.setTextFill(Color.web("#fff"));
        brushSizeLabel.setText(String.valueOf(brushSize));

        Slider brushSizeSlider = new Slider();
        brushSizeSlider.setMin(0);
        brushSizeSlider.setMax(100);
        brushSizeSlider.setValue(brushSize);
        brushSizeSlider.setPadding(new Insets(6,0,0,5));
        brushSizeSlider.valueProperty().addListener((ov, old_val, new_val) -> {
            this.store.setLineWidth(new_val.intValue());
            brushSizeLabel.setText(String.valueOf(new_val.intValue()));
        });


        ColorPicker colorPicker = new ColorPicker(Color.FORESTGREEN);
        colorPicker.setOnAction(e -> this.store.setBrushColor(colorPicker.getValue()));

        this.getChildren().addAll(brushSizeSlider, brushSizeLabel, colorPicker);
    }

    @Override
    public void update(Observable o, Object arg) {
        String[] args = arg.toString().split(" ");
        String operationName = args[0];

        switch (operationName){
            case "lineWidth":
                int operationResult = Integer.valueOf(args[1]);
                this.renderBrushSettings(operationResult);
        }
    }
}
