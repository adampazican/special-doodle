package sample.Pointers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CirclePointer extends Circle implements IPointer {

    public CirclePointer(int radius){
        super(radius/2);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);
        this.setManaged(false);
    }

    public void setSize(int radius, int y)
    {
        this.setRadius(radius/2);
    }

    @Override
    public void setX(double x) {
        this.setCenterX(x + this.getRadius());
    }

    @Override
    public void setY(double y) {
        this.setCenterY(y + this.getRadius());
    }
}
