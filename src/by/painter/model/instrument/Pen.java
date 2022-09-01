package by.painter.model.instrument;

import by.painter.view.paintlayer.PaintCanvas;
import by.painter.view.userinterface.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pen extends DrawingInstrument {

    protected int x1, y1, x2, y2;
    protected Graphics g;

    public Pen(Viewable w) {
        super(w);
        super.adapter = new PenAdapter(w);
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
            painter.setFileSaved(false);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            drawFigure(g);
            mainCanvas.repaint();
        }
        @Override
        public void mouseReleased(MouseEvent event) {
            g.dispose();
            mainCanvas.repaint();
            window.setTitle(painter.getFileName());
            LOGGER.info("Нарисован объект");
            LOGGER.debug("Инструментом: "  + painter.getMainInstrument() +"\nЦветом: " + painter.getInstrumentColor() + "\nВ файл: " + painter.getFileName());
        }
    }

}
