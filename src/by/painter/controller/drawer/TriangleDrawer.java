package by.painter.controller.drawer;

import by.painter.controller.DrawingInstrument;
import by.painter.view.TemporalCanvas;
import by.painter.view.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TriangleDrawer extends DrawingInstrument {

    protected int x1, y1, x2;
    protected final TemporalCanvas temporalCanvas;
    protected Graphics g;

    public TriangleDrawer(Viewable w) {
        super.mainCanvas = w.getMainCanvas();
        super.adapter = new TriangleAdapter();
        super.painter = w.getPainter();
        temporalCanvas = new TemporalCanvas(this);
    }

    @Override
    public void drawFigure(Graphics g) {
        int px = Math.abs((x1 + x2) / 2);
        int py = y1 - (x2 - x1);
        g.setColor(painter.getInstrumentColor());
        g.drawPolygon(new int[]{x1, x2, px}, new int[]{y1, y1, py}, 3);
    }

    private class TriangleAdapter extends MouseAdapter {

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
            temporalCanvas.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mainCanvas.remove(temporalCanvas);
            x2 = e.getX();
            drawFigure(g);
            mainCanvas.repaint();
        }
    }
}
