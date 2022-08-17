package by.painter.controller.filleddrawer;

import by.painter.controller.DrawingInstrument;
import by.painter.view.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FillerDrawer extends DrawingInstrument {

    protected int x1, y1;
    protected Graphics g;

    public FillerDrawer(Viewable w) {
        super.mainCanvas = w.getMainCanvas();
        super.adapter = new FillerAdapter();
        super.painter = w.getPainter();
    }

    @Override
    public void drawFigure(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(painter.getInstrumentColor());

    }

    private class FillerAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            g = mainCanvas.createCanvasGraphics();
            x1 = e.getX();
            y1 = e.getY();
            mainCanvas.repaint();
        }
    }
}
