package by.painter.model;

import by.painter.controller.DrawingInstrument;
import by.painter.controller.FillRectangleDrawer;
import by.painter.controller.PenDrawer;
import by.painter.controller.RectangleDrawer;
import by.painter.view.Viewable;

import java.awt.*;
import java.util.*;

public class Painter {

    private final Color defaultColor = Color.BLACK;
    private Color instrumentColor;
    private DrawingInstrument mainInstrument;
    private final Map<Instrument, DrawingInstrument> instruments;

    public Painter() {
        instruments = new LinkedHashMap<>();
    }

    public void start(Viewable w) {
        w.setVisible(true);
        createInstrumentsFor(w);
    }

    private void createInstrumentsFor(Viewable w) {
        PenDrawer pen = new PenDrawer(w);
        mainInstrument = pen;
        RectangleDrawer rectangle = new RectangleDrawer(w);
        FillRectangleDrawer fillRectangle = new FillRectangleDrawer(w);
        instruments.put(Instrument.PEN, pen);
        instruments.put(Instrument.RECTANGLE, rectangle);
        instruments.put(Instrument.FILL_RECTANGLE, fillRectangle);
    }

    public void setMainInstrument(Instrument instrument) {
        mainInstrument = instruments.get(instrument);
    }

    public DrawingInstrument getMainInstrument() {
        return mainInstrument;
    }

    public Color getInstrumentColor() {
        return instrumentColor == null ? defaultColor : instrumentColor;
    }

    public void setInstrumentColor(Color color) {
        instrumentColor = color;
    }

}
