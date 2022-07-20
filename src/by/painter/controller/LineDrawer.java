package by.painter.controller;

import by.painter.view.TemporalCanvas;
import by.painter.view.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LineDrawer extends DrawingInstrument {

    protected int x1, y1, x2, y2;
    protected final TemporalCanvas temporalCanvas;
    protected Graphics g;

    public LineDrawer(Viewable w) {
        super.mainCanvas = w.getMainCanvas();
        super.adapter = new LineAdapter();
        super.painter = w.getPainter();
        temporalCanvas = new TemporalCanvas(this);
    }

    @Override
    public void drawFigure(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1.0f, 1, 1));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(painter.getInstrumentColor());
        g2d.drawLine(x1, y1, x2, y2);
    }

    private class LineAdapter extends MouseAdapter {

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
