package by.painter.controller.filleddrawer;

import by.painter.controller.drawer.CircleDrawer;
import by.painter.view.Viewable;

import java.awt.*;

public class FillCircleDrawer extends CircleDrawer {

    public FillCircleDrawer(Viewable w) {
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
