package sk.pazican.adam.painter;

import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.Observable;
import java.util.Observer;

/**
 * Trieda (panel) graficky reprezentujúca nastavenia štetca
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public class ToolSettings extends HBox implements Observer {
    private Store store;

    /**
     * Konštruktor triedy nastavuje vzhľad panela.
     * Taktiež sa registruje ako sledovateľ triedy Store.
     */
    public ToolSettings() {
        this.setPadding(new Insets(10, 5, 10, 5));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #222D32;");

        this.store = Store.getInstance();
        this.store.addObserver(this);

        renderBrushSettings(this.store.getLineWidth());
    }

    /**
     * Metóda do panela vykresľuje slider na zmenu veľkosti štetca, label na čiselné zobrazenie veľkosti štetca
     * a colorPicker na zmenu farby štetca.
     * O všetkých zmenách informuje Store.
     *
     * @param brushSize zakladná veľkosť štetca
     */
    public void renderBrushSettings(double brushSize) {
        this.getChildren().removeAll();

        Label brushSizeLabel = new Label();
        brushSizeLabel.setPadding(new Insets(5, 0, 0, 5));
        brushSizeLabel.setTextFill(Color.web("#fff"));
        brushSizeLabel.setText(String.valueOf(brushSize));

        Slider brushSizeSlider = new Slider();
        brushSizeSlider.setMin(0);
        brushSizeSlider.setMax(100);
        brushSizeSlider.setValue(brushSize);
        brushSizeSlider.setPadding(new Insets(6, 0, 0, 5));
        brushSizeSlider.valueProperty().addListener((ov, oldVal, newVal) -> {
            this.store.setLineWidth(newVal.intValue());
            brushSizeLabel.setText(String.valueOf(newVal.intValue()));
        });


        ColorPicker colorPicker = new ColorPicker(Color.FORESTGREEN);
        colorPicker.setOnAction(e -> this.store.setBrushColor(colorPicker.getValue()));

        this.getChildren().addAll(brushSizeSlider, brushSizeLabel, colorPicker);
    }

    /**
     * Metóda, ktorá sa vykoná, keď sa zmení Store
     *
     * @param o sledovaný objekt
     * @param arg argument/y správy
     */
    @Override
    public void update(Observable o, Object arg) {
        String[] args = arg.toString().split(" ");
        String operationName = args[0];

        switch (operationName) {
            case "lineWidth":
                int operationResult = Integer.valueOf(args[1]);
                this.renderBrushSettings(operationResult);
        }
    }
}
