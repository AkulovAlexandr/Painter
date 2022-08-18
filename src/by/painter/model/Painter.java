package by.painter.model;

import by.painter.controller.drawer.DrawingInstrument;
import by.painter.controller.drawer.*;
import by.painter.controller.drawer.filleddrawer.FillCircleDrawer;
import by.painter.controller.drawer.filleddrawer.FillRectangleDrawer;
import by.painter.controller.drawer.filleddrawer.FillTriangleDrawer;
import by.painter.view.PaintCanvas;
import by.painter.view.Viewable;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static java.lang.System.exit;

public class Painter {

    private final Color defaultColor = Color.BLACK;
    private Color instrumentColor;
    private final Map<Instrument, DrawingInstrument> instruments;
    private Viewable window;

    public Painter() {
        instruments = new LinkedHashMap<>();
    }

    public void start() {
        try {
            initializeInstruments();
            setMainInstrument(Instrument.PEN);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Визуальный интерфейс не загружен.", "Критическая ошибка!", JOptionPane.ERROR_MESSAGE);
            exit(0);
        }
    }

    private void initializeInstruments() {
        instruments.put(Instrument.PEN, new PenDrawer(window));
        instruments.put(Instrument.PAINTBRUSH, new PaintbrushDrawer(window));
        instruments.put(Instrument.LINE, new LineDrawer(window));
        instruments.put(Instrument.RECTANGLE, new RectangleDrawer(window));
        instruments.put(Instrument.FILL_RECTANGLE, new FillRectangleDrawer(window));
        instruments.put(Instrument.CIRCLE, new CircleDrawer(window));
        instruments.put(Instrument.FILL_CIRCLE, new FillCircleDrawer(window));
        instruments.put(Instrument.TRIANGLE, new TriangleDrawer(window));
        instruments.put(Instrument.FILL_TRIANGLE, new FillTriangleDrawer(window));
    }

    public void setMainInstrument(Instrument instrument) {
        DrawingInstrument mainInstrument = instruments.get(instrument);
        PaintCanvas mainCanvas = window.getMainCanvas();
        if (mainCanvas.getMouseListeners().length > 0) {
            mainCanvas.removeMouseListener(mainCanvas.getMouseListeners()[0]);
            mainCanvas.removeMouseMotionListener(mainCanvas.getMouseMotionListeners()[0]);
        }
        mainCanvas.addMouseListener(mainInstrument.getMouseAdapter());
        mainCanvas.addMouseMotionListener(mainInstrument.getMouseAdapter());
        mainCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void setWindow(Viewable window) {
        this.window = window;
    }

    public Color getInstrumentColor() {
        return instrumentColor == null ? defaultColor : instrumentColor;
    }

    public void setInstrumentColor(Color color) {
        instrumentColor = color;
    }

}
