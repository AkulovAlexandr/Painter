package by.painter.model.instrument.filledinstrument;

import by.painter.model.instrument.Rectangle;
import by.painter.view.userinterface.Viewable;

import java.awt.*;

public class FillRectangle extends Rectangle {

    public FillRectangle(Viewable w) {
        super(w);
    }

    @Override
    public void drawFigure(Graphics g) {
        int px = Math.min(x1, x2);
        int py = Math.min(y1, y2);
        int pw = Math.abs(x1 - x2);
        int ph = Math.abs(y1 - y2);
        g.setColor(painter.getInstrumentColor());
        g.fillRect(px, py, pw, ph);
    }
}
