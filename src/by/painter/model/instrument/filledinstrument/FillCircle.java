package by.painter.model.instrument.filledinstrument;

import by.painter.model.instrument.Circle;
import by.painter.view.userinterface.Viewable;

import java.awt.*;

public class FillCircle extends Circle {

    public FillCircle(Viewable w) {
        super(w);
    }

    @Override
    public void drawFigure(Graphics g) {
        int px = Math.min(x1, x2);
        int py = Math.min(y1, y2);
        int pw = Math.abs(x1 - x2);
        int ph = Math.abs(y1 - y2);
        g.setColor(painter.getInstrumentColor());
        g.fillOval(px, py, pw, ph);
    }
}
