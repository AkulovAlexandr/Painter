package by.painter.controller;

import by.painter.view.PaintCanvas;
import by.painter.view.TemporalCanvas;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class DrawingInstrument {
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
