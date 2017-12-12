package sk.pazican.adam.painter;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Trieda reprezentujúca kresliacu vrstvu
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public class PaintingCanvas extends Canvas {
    private GraphicsContext gc;
    private LineDrawer lineDrawer;
    private ILineDrawingMethod squareLine;
    private ILineDrawingMethod squareEraser;
    private ILineDrawingMethod circleLine;

    /**
     * Konštruktor, ktorý sa stará o inicializáciu triedy lineDrawer a lambda funkcií používaných objektom lineDrawer
     *
     * @param width šírka vrstvy
     * @param height výška vrstvy
     */
    public PaintingCanvas(int width, int height) {
        super(width, height);

        this.gc = this.getGraphicsContext2D();
        this.gc.setFill(Color.FORESTGREEN);

        this.lineDrawer = new LineDrawer();

        this.squareLine = (x0, y0, w, h) -> gc.fillRect(x0, y0, 4, 4);
        this.circleLine = (x0, y0, w, h) -> gc.fillOval(x0, y0, w, h);
        this.squareEraser = (x0, y0, w, h) -> gc.clearRect(x0, y0, 4, 4);
    }

    /**
     * Metóda používajúca triedu lineDrawer na vykreslenie čiary
     *
     * @param x0 začiatočný x
     * @param y0 začiatočný y
     * @param x1 konečný x
     * @param y1 konečný y
     */
    protected void drawLine(double x0, double y0, double x1, double y1) {
        this.lineDrawer.drawLine(x0, y0, x1, y1, this.circleLine);
    }

    /**
     * Metóda používajúca triedu lineDrawer na vymazanie čiary
     *
     * @param x0 začiatočný x
     * @param y0 začiatočný y
     * @param x1 konečný x
     * @param y1 konečný y
     */
    protected void clearLine(double x0, double y0, double x1, double y1) {
        this.lineDrawer.drawLine(x0, y0, x1, y1, this.squareEraser);
    }

    /**
     * Metóda meniaca hrúbku tvorenej čiary
     *
     * @param newLineWidth nová hrúbka čiary
     */
    public void setLineWidth(int newLineWidth) {
        this.squareLine = (x0, y0, w, h) -> gc.fillRect(x0, y0, newLineWidth, newLineWidth);
        this.circleLine = (x0, y0, w, h) -> gc.fillOval(x0, y0, newLineWidth, newLineWidth);
        this.squareEraser = (x0, y0, w, h) -> gc.clearRect(x0, y0, newLineWidth, newLineWidth);
    }

    /**
     * Metóda meniaca farbu tvorenej čiary
     *
     * @param newColor nová farba čiary
     */
    public void setLineColor(Color newColor) {
        this.gc.setFill(newColor);
    }
}






























