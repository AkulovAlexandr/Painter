package by.painter.controller;

import by.painter.view.TemporalCanvas;
import by.painter.view.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CircleDrawer extends DrawingInstrument {

    protected int x1, y1, x2, y2;
    protected final TemporalCanvas temporalCanvas;
    protected Graphics g;

    public CircleDrawer(Viewable w) {
        super.mainCanvas = w.getMainCanvas();
        super.adapter = new RectangleAdapter();
        super.painter = w.getPainter();
        temporalCanvas = new TemporalCanvas(this);
    }

    @Override
    public void drawFigure(Graphics g) {
        int px = Math.min(x1, x2);
        int py = Math.min(y1, y2);
        int pw = Math.abs(x1 - x2);
        int ph = Math.abs(y1 - y2);
        g.setColor(painter.getInstrumentColor());
        g.drawOval(px, py, pw, ph);
    }

    private class RectangleAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            g = mainCanvas.createCanvasGraphics();
            temporalCanvas.setBounds(mainCanvas.getX(), mainCanvas.getY(), mainCanvas.getWidth(),
                    mainCanvas.getHeight());
            mainCanvas.add(temporalCanvas);
            x1 = e.getX();
            y1 = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();
            temporalCanvas.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mainCanvas.remove(temporalCanvas);
            x2 = e.getX();
            y2 = e.getY();
            drawFigure(g);
            mainCanvas.repaint();
        }
    }
}
