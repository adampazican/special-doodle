package sk.pazican.adam.painter.pointers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquarePointer extends Rectangle implements IPointer {
    public SquarePointer(int width) {
        this(width, width);
    }

    public SquarePointer(int width, int height) {
        super(width, height);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);
        this.setManaged(false);
    }

    public void setSize(int width, int height) {
        this.setHeight(width);
        this.setWidth(height);
    }
}
