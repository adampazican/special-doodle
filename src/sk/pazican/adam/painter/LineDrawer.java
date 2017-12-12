package sk.pazican.adam.painter;

/**
 * Trieda ktorá sa stará o vykresľovanie nových čiar
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public class LineDrawer {
    /**
     * Konvertuje argumenty z double na int
     *
     * @param x0 začiatočné x
     * @param y0 začiatočné y
     * @param x1 koncové x
     * @param y1 koncové y
     * @param drawLine Lambda metóda na vykreslenie jednej časti čiary (štvorec, kruh, ...)
     */
    public void drawLine(double x0, double y0, double x1, double y1, ILineDrawingMethod drawLine) {
        this.drawLine((int)x0, (int)y0, (int)x1, (int)y1, drawLine);
    }

    /**
     * Vykresľuje čiari pomocou Bresenhamovho algoritmu.
     *
     * @param x0 začiatočné x
     * @param y0 začiatočné y
     * @param x1 koncové x
     * @param y1 koncové y
     * @param drawLine Lambda metóda na vykreslenie jednej časti čiary (štvorec, kruh, ...)
     */
    public void drawLine(int x0, int y0, int x1, int y1, ILineDrawingMethod drawLine) {
        int deltaX = Math.abs(x1 - x0);
        int deltaY = -Math.abs(y1 - y0);

        int slopeX = x0 < x1 ? 1 : -1;
        int slopeY = y0 < y1 ? 1 : -1;

        int deltaErr = deltaX + deltaY;
        int err = 0;

        while (true) {
            drawLine.drawLine(x0, y0, 4, 4);
            if (x0 == x1 && y0 == y1) {
                break;
            }
            err = 2 * deltaErr;
            if (err >= deltaY) {
                deltaErr += deltaY;
                x0 += slopeX;
            }
            if (err <= deltaX) {
                deltaErr += deltaX;
                y0 += slopeY;
            }
        }
    }
}
