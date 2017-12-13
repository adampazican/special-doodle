package sk.pazican.adam.painter;

import javafx.scene.paint.Color;
import java.util.Observable;

/**
 * Singleton trieda, ktorá reprezentuje stav programu.
 * Je observable takže ďalšie objekty môžu prijímať správy, keď sa stav zmení.
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public final class Store extends Observable {
    private static Store instance = null;
    private int lineWidth = 5;
    private Color brushColor = Color.FORESTGREEN;

    /**
     * Private konštruktor, aby trieda nemohla byť inicializovaná nikým iným len sebou
     */
    private Store() { }

    /**
     * Metóda vracia svoju inštanciu, poprípade vytvorí novú ak neexistuje
     *
     * @return Vracia svoju inštanciu
     */
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    /**
     * Zmení hrúbku čiary a upozorní sledovateľov objektu
     *
     * @param newLineWidth nová hrúbka čiary
     */
    public void setLineWidth(int newLineWidth) {
        this.lineWidth = newLineWidth;
        setChanged();
        notifyObservers("linewidth " + newLineWidth);
    }

    /**
     * Zmení farbu štetca a upozorní sledovateľov
     *
     * @param newBrushColor nová farba štetca
     */
    public void setBrushColor(Color newBrushColor) {
        this.brushColor = newBrushColor;
        setChanged();
        notifyObservers("brushcolor " + newBrushColor);
    }

    /**
     * Vráti hrúbku čiary
     *
     * @return hrúbka čiary
     */
    public int getLineWidth() {
        return this.lineWidth;
    }
}
