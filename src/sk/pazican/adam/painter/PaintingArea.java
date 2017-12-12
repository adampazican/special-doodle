package sk.pazican.adam.painter;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sk.pazican.adam.painter.pointers.CirclePointer;
import sk.pazican.adam.painter.pointers.IPointer;
import sk.pazican.adam.painter.pointers.SquarePointer;
import java.util.Observable;
import java.util.Observer;

/**
 * Trieda manažujúca triedy paintingCanvas a pointer
 *
 * @author Adam Pažičan
 * @version 2017.12.12
 */
public class PaintingArea extends StackPane implements Observer {
    private IPointer pointer;
    private PaintingCanvas paintingCanvas;
    private double oldX;
    private double oldY;
    private boolean dragging;
    private double zoom = 1.0;
    private final double zoomFactor = 2;
    private Store store;

    /**
     * Konštruktor vytvára nový kurzor (pointer) a novú kresliacu vrstvu (paintingCanvas).
     * Taktiež sa prihlasuje ako sledovateľ triedy Store.
     */
    public PaintingArea() {
        this.pointer = new CirclePointer(5);
        this.paintingCanvas = new PaintingCanvas(800, 500);
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setPrefWidth(800);
        this.setPrefHeight(500);

        this.store = Store.getInstance();
        this.store.addObserver(this);
        this.store.setLineWidth(5);


        this.getChildren().addAll(this.paintingCanvas, (Node)this.pointer);

        /* Nastavovanie eventov */
        this.setOnMouseMoved(this::onMouseMoved);
        this.setOnMouseDragged(this::onMouseDragged);
        this.setOnMouseClicked(this::onMouseClicked);
        this.setOnMouseExited(this::onMouseExited);
        this.setOnMouseEntered(this::onMouseEntered);
        this.setOnScroll(this::onScroll);
        this.setOnMousePressed(this::onMousePressed);
    }

    /**
     * Metóda vykonávaná, keď sa zmení stav triedy store.
     * Deleguje zmeny Storu objektom paintingCanvas a pointer.
     *
     * @param o sledovaný objekt
     * @param arg argument správy
     */
    @Override
    public void update(Observable o, Object arg) {
        String[] args = arg.toString().split(" ");
        String operationName = args[0];

        switch (operationName) {
            case "linewidth": {
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

    /**
     * Metóda vykonávaná, keď sa v paneli pohne myšou
     * Ukladá súradnice myši a mení poziciu kurzora.
     *
     * @param e vykonaný MouseEvent
     */
    private void onMouseMoved(MouseEvent e) {
        this.oldX = e.getX();
        this.oldY = e.getY();
        this.pointer.setX(e.getX());
        this.pointer.setY(e.getY());
    }

    /**
     * Metóda vykonávaná, keď sa v paneli ťahá myš (klik + pohyb)
     * Pri ľavom tlačidle vykresľuje novú čiaru.
     * Pri pravom tlačidle vymazáva čiaru (guma).
     * Pri strednom tlačidle posúva plátno po obrazovke.
     *
     * @param e vykonaný MouseEvent
     */
    private void onMouseDragged(MouseEvent e) {
        if (!this.dragging) {
            this.dragging = true;
        }

        switch (e.getButton()) {
            case PRIMARY:
                this.paintingCanvas.drawLine(this.oldX, this.oldY, e.getX(), e.getY());
                break;
            case SECONDARY:
                this.paintingCanvas.clearLine(this.oldX, this.oldY, e.getX(), e.getY());
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

    /**
     * Metóda vykonávaná, keď sa v paneli klikne
     * Pri ľavom tlačidle nakreslí štvorec.
     * Pri pravom tlačidle vymaže štvorec.
     * Ak bol kurzor zmenení na štvorec (guma), zmení ho späť na kruh (štetec).
     *
     * @param e vykonaný MouseEvent
     */
    private void onMouseClicked(MouseEvent e) {
        if (!this.dragging) {
            switch (e.getButton()) {
                case PRIMARY:
                    this.paintingCanvas.drawLine(e.getX(), e.getY(), e.getX(), e.getY());
                    break;
                case SECONDARY:
                    this.paintingCanvas.clearLine(e.getX(), e.getY(), e.getX(), e.getY());
                    break;
            }
        } else {
            this.dragging = false;
        }

        this.getChildren().remove(this.pointer);
        this.pointer = new CirclePointer(this.store.getLineWidth());
        this.pointer.setX(e.getX());
        this.pointer.setY(e.getY());
        this.getChildren().add((Node)this.pointer);
    }

    /**
     * Metóda vykonávaná, keď myš opustí panel.
     * Zmení sa kurzor na zakládny kurzor OS.
     *
     * @param e vykonaný MouseEvent
     */
    private void onMouseExited(MouseEvent e) {
        this.getScene().setCursor(Cursor.DEFAULT);
        this.pointer.setVisible(false);
    }

    /**
     * Metóda vykonávaná, keď myš vstúpi do panela.
     * Zmení sa kurzor na vlastný kurzor programu.
     *
     * @param e vykonaný MouseEvent
     */
    private void onMouseEntered(MouseEvent e) {
        this.getScene().setCursor(Cursor.NONE);
        this.pointer.setVisible(true);
    }

    /**
     * Metóda vykonávaná, keď užívateľ scrollne myšou.
     * Mení priblíženie plátna.
     * Scroll hore plátno priblíži, a scroll dole ho oddiali.
     *
     * @param e vykonaný ScrollEvent
     */
    private void onScroll(ScrollEvent e) {
        double zoomBy = e.getDeltaY() > 0 ? this.zoom * this.zoomFactor
                : this.zoom / this.zoomFactor;
        this.setScaleX(zoomBy);
        this.setScaleY(zoomBy);
        this.zoom = zoomBy;
    }

    /**
     * Metóda vykonávaná, práve keď sa stlači tlačidlo myši.
     * Mení typ kurzoru ak je stlačené prave tlačidlo (guma).
     *
     * @param e vykonaný MouseEvent
     */
    private void onMousePressed(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY) {
            this.getChildren().remove(this.pointer);
            this.pointer = new SquarePointer(this.store.getLineWidth());
            this.getChildren().add((Node)this.pointer);
            this.pointer.setX(e.getX());
            this.pointer.setY(e.getY());
        }
    }

}
