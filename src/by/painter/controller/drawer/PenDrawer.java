package by.painter.controller.drawer;

import by.painter.view.PaintCanvas;
import by.painter.view.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PenDrawer extends DrawingInstrument {

    protected int x1, y1, x2, y2;
    protected Graphics g;

    public PenDrawer(Viewable w) {
        super.adapter = new PenAdapter(w);
        super.painter = w.getPainter();
    }

    @Override
    public void drawFigure(Graphics g) {
        g.setColor(painter.getInstrumentColor());
        g.drawLine(x1, y1, x2, y2);
        x2 = x1;
        y2 = y1;
    }

    private class PenAdapter extends MouseAdapter {
        private final PaintCanvas mainCanvas;

        private PenAdapter(Viewable viewable) {
            mainCanvas = viewable.getMainCanvas();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            g = mainCanvas.createCanvasGraphics();
            x1 = e.getX();
            y1 = e.getY();
            x2 = x1;
            y2 = y1;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            drawFigure(g);
            mainCanvas.repaint();
        }

    }

}
