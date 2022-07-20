package by.painter.controller;

import by.painter.view.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintbrushDrawer extends PenDrawer {

    public PaintbrushDrawer(Viewable w) {
        super(w);
    }

    @Override
    public void drawFigure(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10.0f, 1, 1));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(painter.getInstrumentColor());
        g2d.drawLine(x1, y1, x2, y2);
        x2 = x1;
        y2 = y1;
    }

}
