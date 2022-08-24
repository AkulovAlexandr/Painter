package by.painter.model;

import by.painter.model.instrument.*;
import by.painter.model.instrument.Rectangle;
import by.painter.model.instrument.filledinstrument.FillCircle;
import by.painter.model.instrument.filledinstrument.FillRectangle;
import by.painter.model.instrument.filledinstrument.FillTriangle;
import by.painter.view.paintlayer.PaintCanvas;
import by.painter.view.userinterface.Viewable;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static java.lang.System.exit;

public class Painter {

    private String fileName;
    private boolean isFileSaved;
    private final Color defaultColor = Color.BLACK;
    private Color instrumentColor;
    private final Map<Instrument, DrawingInstrument> instruments;
    private Viewable window;

    public Painter() {
        instruments = new LinkedHashMap<>();
    }

    public void start() {
        try {
            isFileSaved = true;
            initializeInstruments();
            setMainInstrument(Instrument.PEN);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Визуальный интерфейс не загружен.", "Критическая ошибка!", JOptionPane.ERROR_MESSAGE);
            exit(0);
        }
    }

    private void initializeInstruments() {
        instruments.put(Instrument.PEN, new Pen(window));
        instruments.put(Instrument.PAINTBRUSH, new Paintbrush(window));
        instruments.put(Instrument.LINE, new Line(window));
        instruments.put(Instrument.RECTANGLE, new Rectangle(window));
        instruments.put(Instrument.FILL_RECTANGLE, new FillRectangle(window));
        instruments.put(Instrument.CIRCLE, new Circle(window));
        instruments.put(Instrument.FILL_CIRCLE, new FillCircle(window));
        instruments.put(Instrument.TRIANGLE, new Triangle(window));
        instruments.put(Instrument.FILL_TRIANGLE, new FillTriangle(window));
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

    public boolean isFileSaved() {
        return isFileSaved;
    }

    public void setFileSaved(boolean fileSaved) {
        isFileSaved = fileSaved;
    }

    public String getFileName() {
        return (fileName == null) ? "new picture.png" : fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
