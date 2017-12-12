package sk.pazican.adam.painter.pointers;

/**
 * Interface opisujúci metódy kurzora.
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public interface IPointer {
    void setSize(int width, int height);
    void setX(double x);
    void setY(double y);
    void setVisible(boolean visibility);
}
