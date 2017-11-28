package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class PaintingCanvas extends Canvas{
    private GraphicsContext gc;
    private LineDrawer lineDrawer;
    private ILineDrawingMethod squareLine;
    private ILineDrawingMethod squareEraser;
    private ILineDrawingMethod circleLine;

    public PaintingCanvas(int x, int y){
        super(x, y);

        this.gc = this.getGraphicsContext2D();
        this.gc.getPixelWriter().setColor(100, 100, Color.BLACK);

        this.lineDrawer = new LineDrawer();

        this.squareLine = (x0, y0, w, h) -> gc.fillRect(x0, y0, 4, 4);
        this.circleLine = (x0, y0, w, h) -> gc.fillOval(x0, y0, w, h);
        this.squareEraser = (x0, y0, w, h) -> gc.clearRect(x0, y0, 4, 4);

        drawRectangles();
    }

    protected void drawLine(double x0, double y0, double x1, double y1){
        this.lineDrawer.drawLine(x0, y0, x1, y1, circleLine);
    }

    protected void clearLine(double x0, double y0, double x1, double y1){
        this.lineDrawer.drawLine(x0, y0, x1, y1, squareEraser);
    }

    public void setLineWidth(int newLineWidth){
        this.squareLine = (x0, y0, w, h) -> gc.fillRect(x0, y0, newLineWidth, newLineWidth);
        this.circleLine = (x0, y0, w, h) -> gc.fillOval(x0, y0, newLineWidth, newLineWidth);
        this.squareEraser = (x0, y0, w, h) -> gc.clearRect(x0, y0, newLineWidth, newLineWidth);
    }

    public void setLineColor(Color newColor){
        this.gc.setFill(newColor);
    }


    private void drawRectangles(){
        for(int i = 0; i <= 10; i++){
            float alpha = i * 0.1f;

            this.gc.setFill(Color.FORESTGREEN);
            this.gc.setGlobalAlpha(alpha);
            this.gc.fillRect(50*i,20,40,40);
        }
    }
}






























