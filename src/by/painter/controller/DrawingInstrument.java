package by.painter.controller;

import by.painter.model.Painter;
import by.painter.view.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class DrawingInstrument {

    protected Painter painter;
    protected PaintCanvas mainCanvas;
    protected MouseAdapter adapter;

    public void setUpDrawMethod() {
        if (mainCanvas.getMouseListeners().length > 0) {
            mainCanvas.removeMouseListener(mainCanvas.getMouseListeners()[0]);
            mainCanvas.removeMouseMotionListener(mainCanvas.getMouseMotionListeners()[0]);
        }
        mainCanvas.addMouseListener(adapter);
        mainCanvas.addMouseMotionListener(adapter);
        mainCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void drawFigure(Graphics g) {

    }

}
