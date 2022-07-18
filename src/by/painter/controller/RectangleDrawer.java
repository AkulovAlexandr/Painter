package by.painter.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import by.painter.view.PaintCanvas;
import by.painter.view.TemporalCanvas;
import by.painter.view.Viewable;

public class RectangleDrawer extends DrawingInstrument {

    private int x1, y1, x2, y2;
    private final TemporalCanvas temporalCanvas;
    private Graphics g;

    public RectangleDrawer(Viewable w) {
        super.mainCanvas = w.getMainCanvas();
        super.adapter = new RectangleAdapter();
        temporalCanvas = new TemporalCanvas(this);
    }

    public static void deleteAdapter(PaintCanvas mainCanvas, MouseAdapter penAdapter) {
    }

    @Override
    public void drawFigure(Graphics gr) {
        int px = Math.min(x1, x2);
        int py = Math.min(y1, y2);
        int pw = Math.abs(x1 - x2);
        int ph = Math.abs(y1 - y2);
        gr.setColor(Color.RED);
        gr.drawRect(px, py, pw, ph);
    }

    private class RectangleAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            g = mainCanvas.createCanvasGraphics();
            temporalCanvas.setBounds(mainCanvas.getX() - 20, mainCanvas.getY() - 23, mainCanvas.getWidth(),
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
