package by.painter.model.instrument;

import by.painter.view.paintlayer.PaintCanvas;
import by.painter.view.paintlayer.TemporalCanvas;
import by.painter.view.userinterface.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Triangle extends DrawingInstrument {

    protected int x1, y1, x2;
    protected final TemporalCanvas temporalCanvas;
    protected Graphics g;

    public Triangle(Viewable w) {
        super(w);
        super.adapter = new TriangleAdapter(w);
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
        private final PaintCanvas mainCanvas;

        private TriangleAdapter(Viewable viewable) {
            mainCanvas = viewable.getMainCanvas();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            g = mainCanvas.createCanvasGraphics();
            temporalCanvas.setSize(mainCanvas.getWidth(), mainCanvas.getHeight());
            mainCanvas.add(temporalCanvas);
            x1 = e.getX();
            y1 = e.getY();
            x2 = e.getX();
            painter.setFileSaved(false);
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
            g.dispose();
            mainCanvas.repaint();
            window.setTitle(painter.getFileName());
            LOGGER.info("Нарисован объект");
            LOGGER.debug("Инструментом: "  + painter.getMainInstrument() +"\nЦветом: " + painter.getInstrumentColor() + "\nВ файл: " + painter.getFileName());
        }
    }
}
