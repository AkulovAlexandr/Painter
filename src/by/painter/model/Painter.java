package by.painter.model;

import by.painter.model.instrument.*;
import by.painter.model.instrument.Rectangle;
import by.painter.model.instrument.filledinstrument.FillCircle;
import by.painter.model.instrument.filledinstrument.FillRectangle;
import by.painter.model.instrument.filledinstrument.FillTriangle;
import by.painter.view.paintlayer.PaintCanvas;
import by.painter.view.userinterface.Viewable;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.*;


public class Painter {

    private final static Logger LOGGER = Logger.getLogger("log");
    private String fileName;
    DrawingInstrument mainInstrument;
    private boolean isFileSaved;
    private final Color defaultColor = Color.BLACK;
    private Color instrumentColor;
    private final Map<Instrument, DrawingInstrument> instruments;
    private Viewable window;

    public Painter() {
        instruments = new LinkedHashMap<>();
        isFileSaved = true;
    }

    public void start() throws NullPointerException {
        LOGGER.info("Старт программы...");
        initializeInstruments();
        setMainInstrument(Instrument.PEN);
    }

    private void initializeInstruments() throws NullPointerException {
        LOGGER.info("Инициализация инструментов...");
        instruments.put(Instrument.PEN, new Pen(window));
        instruments.put(Instrument.PAINTBRUSH, new Paintbrush(window));
        instruments.put(Instrument.LINE, new Line(window));
        instruments.put(Instrument.RECTANGLE, new Rectangle(window));
        instruments.put(Instrument.FILL_RECTANGLE, new FillRectangle(window));
        instruments.put(Instrument.CIRCLE, new Circle(window));
        instruments.put(Instrument.FILL_CIRCLE, new FillCircle(window));
        instruments.put(Instrument.TRIANGLE, new Triangle(window));
        instruments.put(Instrument.FILL_TRIANGLE, new FillTriangle(window));
        LOGGER.debug("Созданы " + instruments.size() + " объектов:\n" + instruments.values());
    }

    public void setMainInstrument(Instrument instrument) {
        mainInstrument = instruments.get(instrument);
        PaintCanvas mainCanvas = window.getMainCanvas();
        if (mainCanvas.getMouseListeners().length > 0) {
            mainCanvas.removeMouseListener(mainCanvas.getMouseListeners()[0]);
            mainCanvas.removeMouseMotionListener(mainCanvas.getMouseMotionListeners()[0]);
        }
        mainCanvas.addMouseListener(mainInstrument.getMouseAdapter());
        mainCanvas.addMouseMotionListener(mainInstrument.getMouseAdapter());
        mainCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        LOGGER.debug(instrument + " - задан, как текущий инструмент для рисования");
    }

    public DrawingInstrument getMainInstrument() {
        return mainInstrument;
    }

    public boolean isFileSaved() {
        return isFileSaved;
    }

    public void setFileSaved(boolean fileSaved) {
        isFileSaved = fileSaved;
        LOGGER.debug("Текущие изменения сохранены? - " + isFileSaved);
    }

    public String getFileName() {
        return (fileName == null) ? "Новое изображение.png" : fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setWindow(Viewable window) {
        this.window = window;
        LOGGER.debug("Программе задан графический интерфейс: " + window.getClass());
    }

    public Color getInstrumentColor() {
        return instrumentColor == null ? defaultColor : instrumentColor;
    }

    public void setInstrumentColor(Color color) {
        instrumentColor = color;
        LOGGER.debug("Выбран цвет: " + color);
    }

}
