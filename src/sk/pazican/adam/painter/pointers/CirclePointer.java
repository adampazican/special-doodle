package sk.pazican.adam.painter.pointers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Trieda reprezentuje kruhový kurzor pre kresliacu plochu
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public class CirclePointer extends Circle implements IPointer {

    /**
     * Konštruktor nastavuje defaultné nastavenia kurzora
     *
     * @param radius rádius kurzora
     */
    public CirclePointer(int radius) {
        super(radius / 2);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);
        this.setManaged(false); // node je vylúčený z layoutových kalkulácií rodiča
    }

    /**
     * Zmení veľkosť kurzora
     *
     * @param radius nový radius kurzora
     * @param y v tejto triede nerobi nič ale musí byť kvôli kompatibilite s interfaceom IPointer
     */
    @Override
    public void setSize(int radius, int y) {
        this.setRadius(radius / 2);
    }

    /**
     * Zmení súradnicu x kurzora
     *
     * @param x nové x kurzora
     */
    @Override
    public void setX(double x) {
        this.setCenterX(x + this.getRadius());
    }

    /**
     * Zmení súradnicu y kurzora
     *
     * @param y nové y kurzora
     */
    @Override
    public void setY(double y) {
        this.setCenterY(y + this.getRadius());
    }
}
