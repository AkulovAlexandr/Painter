package by.painter.model;

import by.painter.controller.DrawingInstrument;
import by.painter.controller.drawer.*;
import by.painter.controller.filleddrawer.FillCircleDrawer;
import by.painter.controller.filleddrawer.FillRectangleDrawer;
import by.painter.controller.filleddrawer.FillTriangleDrawer;
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
        PaintbrushDrawer paintBrush = new PaintbrushDrawer(w);
        LineDrawer line = new LineDrawer(w);
        RectangleDrawer rectangle = new RectangleDrawer(w);
        FillRectangleDrawer fillRectangle = new FillRectangleDrawer(w);
        CircleDrawer circle = new CircleDrawer(w);
        FillCircleDrawer fillCircle = new FillCircleDrawer(w);
        TriangleDrawer triangle = new TriangleDrawer(w);
        TriangleDrawer fillTriangle = new FillTriangleDrawer(w);
        instruments.put(Instrument.PEN, pen);
        instruments.put(Instrument.PAINTBRUSH, paintBrush);
        instruments.put(Instrument.LINE, line);
        instruments.put(Instrument.RECTANGLE, rectangle);
        instruments.put(Instrument.FILL_RECTANGLE, fillRectangle);
        instruments.put(Instrument.CIRCLE, circle);
        instruments.put(Instrument.FILL_CIRCLE, fillCircle);
        instruments.put(Instrument.TRIANGLE, triangle);
        instruments.put(Instrument.FILL_TRIANGLE, fillTriangle);
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
