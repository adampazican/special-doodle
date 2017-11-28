package sample;

import javafx.scene.paint.Color;

import java.util.Observable;

public final class Store extends Observable{
    private static Store instance = null;
    private int lineWidth = 4;
    private Color brushColor = Color.FORESTGREEN;

    private Store(){}

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }

    public void setLineWidth(int newLineWidth){
        this.lineWidth = newLineWidth;
        setChanged();
        notifyObservers("linewidth " + newLineWidth);
    }

    public void setBrushColor(Color newBrushColor){
        this.brushColor = newBrushColor;
        setChanged();
        notifyObservers("brushcolor " + newBrushColor);
    }

    public int getLineWidth(){
        return lineWidth;
    }
}
