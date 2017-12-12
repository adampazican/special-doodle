package sk.pazican.adam.painter.pointers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Trieda reprezentuje štvorcový kurzor pre kresliacu plochu
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public class SquarePointer extends Rectangle implements IPointer {
    /**
     * Volá druhý konštruktor s rovnakou výškou a šírkou (štvorec)
     *
     * @param width šírka kurzora
     */
    public SquarePointer(int width) {
        this(width, width);
    }

    /**
     * Konštruktor nastavuje defaultné nastavenia kurzora
     *
     * @param width šírka kurzora
     * @param height výška kurzora
     */
    public SquarePointer(int width, int height) {
        super(width, height);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);
        this.setManaged(false);
    }

    /**
     * Mení rozmery kurzora
     *
     * @param width nová šírka
     * @param height nová výška
     */
    @Override
    public void setSize(int width, int height) {
        this.setHeight(width);
        this.setWidth(height);
    }
}
