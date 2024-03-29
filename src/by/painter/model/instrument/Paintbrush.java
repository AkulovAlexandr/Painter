package by.painter.model.instrument;

import by.painter.view.userinterface.Viewable;

import java.awt.*;

public class Paintbrush extends Pen {

    public Paintbrush(Viewable w) {
        super(w);
    }

    @Override
    public void drawFigure(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(painter.getInstrumentColor());
        g2d.drawLine(x1, y1, x2, y2);
        x2 = x1;
        y2 = y1;
    }

}
