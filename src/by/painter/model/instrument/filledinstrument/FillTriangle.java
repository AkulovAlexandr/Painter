package by.painter.model.instrument.filledinstrument;

import by.painter.model.instrument.Triangle;
import by.painter.view.userinterface.Viewable;

import java.awt.*;

public class FillTriangle extends Triangle {

    public FillTriangle(Viewable w) {
        super(w);
    }

    @Override
    public void drawFigure(Graphics g) {
        int px = Math.abs((x1 + x2) / 2);
        int py = y1 - (x2 - x1);
        g.setColor(painter.getInstrumentColor());
        g.fillPolygon(new int[]{x1, x2, px}, new int[]{y1, y1, py}, 3);
    }
}
