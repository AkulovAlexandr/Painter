package by.painter.model;

import by.painter.controller.DrawingInstrument;
import by.painter.controller.PenDrawer;
import by.painter.controller.RectangleDrawer;
import by.painter.view.Viewable;
import by.painter.view.Window;

import java.util.*;

public class Painter {

    private DrawingInstrument mainInstrument;
    private final Map<Instrument, DrawingInstrument> instruments;

    public Painter() {
        instruments = new LinkedHashMap<>();
    }

    public void start(Viewable w) {
        w.setPainter(this);
        w.setVisible(true);
        createInstrumentsFor(w);
    }

    private void createInstrumentsFor(Viewable w) {
        PenDrawer pen = new PenDrawer(w);
        RectangleDrawer rectangle = new RectangleDrawer(w);
        instruments.put(Instrument.PEN, pen);
        instruments.put(Instrument.RECTANGLE, rectangle);
    }

    public void setMainInstrument(Instrument instrument) {
        mainInstrument = instruments.get(instrument);
        mainInstrument.setUpDrawMethod();
    }


    public DrawingInstrument getMainInstrument() {
        return mainInstrument;
    }

}
