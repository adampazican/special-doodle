package sample;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sample.Pointers.CirclePointer;
import sample.Pointers.IPointer;

import java.util.Observable;
import java.util.Observer;

public class PaintingArea extends StackPane implements Observer {
    private IPointer pointer;
    private PaintingCanvas paintingCanvas;
    protected double oldX;
    protected double oldY;
    private boolean dragging;
    private double zoom = 1.0;
    private final double zoomFactor = 2;

    public PaintingArea(){
        this.pointer = new CirclePointer(4);
        this.paintingCanvas = new PaintingCanvas(800, 500);
        this.setStyle("-fx-background-color: #FFFF00;");
        this.setPrefWidth(800);
        this.setPrefHeight(500);

        Store store = Store.getInstance();
        store.addObserver(this);
        store.setLineWidth(5);


        this.getChildren().addAll(paintingCanvas, (Node) pointer);

        this.setOnMouseMoved(e -> onMouseMoved(e));
        this.setOnMouseDragged(e -> onMouseDragged(e));
        this.setOnMouseClicked(e -> onMouseClicked(e));
        this.setOnMouseExited(e -> onMouseExited(e));
        this.setOnMouseEntered(e -> onMouseEntered(e));
        this.setOnScroll(e -> onScroll(e));
    }

    @Override
    public void update(Observable o, Object arg) {
        String[] args = arg.toString().split(" ");
        String operationName = args[0];

        switch (operationName){
            case "linewidth":{
                int operationResult = Integer.valueOf(args[1]);
                this.paintingCanvas.setLineWidth(operationResult);
                this.pointer.setSize(operationResult, operationResult);
                return;
            }
            case "brushcolor": {
                Color operationResult = Color.web(args[1]);
                this.paintingCanvas.setLineColor(operationResult);
            }
        }
    }

    private void onMouseMoved(MouseEvent e){
        this.oldX = e.getX();
        this.oldY = e.getY();
        this.pointer.setX(e.getX());
        this.pointer.setY(e.getY());
    }

    private void onMouseDragged(MouseEvent e){
        if(!this.dragging) {
            this.dragging = true;
        }

        switch (e.getButton()){
            case PRIMARY:
                this.paintingCanvas.drawLine(oldX, oldY, e.getX(), e.getY());
                break;
            case SECONDARY:
                this.paintingCanvas.clearLine(oldX, oldY, e.getX(), e.getY());
                break;
            case MIDDLE:
                this.setTranslateX(this.getTranslateX() + (e.getX() - this.oldX));
                this.setTranslateY(this.getTranslateY() + (e.getY() - this.oldY));
                return;
        }

        this.oldX = e.getX();
        this.oldY = e.getY();
        this.pointer.setX(e.getX());
        this.pointer.setY(e.getY());
    }

    public void onMouseClicked(MouseEvent e){
        if(!this.dragging) {
            switch (e.getButton()){
                case PRIMARY:
                    this.paintingCanvas.drawLine(e.getX(), e.getY(), e.getX(), e.getY());
                    return;
                case SECONDARY:
                    this.paintingCanvas.clearLine(e.getX(), e.getY(), e.getX(), e.getY());
                    return;
            }
        }
        else {
            this.dragging = false;
        }
    }

    private void onMouseExited(MouseEvent e){
        this.getScene().setCursor(Cursor.DEFAULT);
        this.pointer.setVisible(false);
    }

    private void onMouseEntered(MouseEvent e){
        this.getScene().setCursor(Cursor.NONE);
        this.pointer.setVisible(true);
    }

    public void onScroll(ScrollEvent e){
        double zoomBy = e.getDeltaY() > 0 ? this.zoom * this.zoomFactor
                : this.zoom / this.zoomFactor;
        this.setScaleX(zoomBy);
        this.setScaleY(zoomBy);
        this.zoom = zoomBy;
    }

}
