package by.painter.model.instrument;

import by.painter.view.paintlayer.PaintCanvas;
import by.painter.view.paintlayer.TemporalCanvas;
import by.painter.view.userinterface.Viewable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Line extends DrawingInstrument {

    protected int x1, y1, x2, y2;
    protected final TemporalCanvas temporalCanvas;
    protected Graphics g;

    public Line(Viewable w) {
        super(w);
        super.adapter = new LineAdapter(w);
        temporalCanvas = new TemporalCanvas(this);
    }

    @Override
    public void drawFigure(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(painter.getInstrumentColor());
        g2d.drawLine(x1, y1, x2, y2);
    }

    private class LineAdapter extends MouseAdapter {
        private final PaintCanvas mainCanvas;

        private LineAdapter(Viewable viewable) {
            mainCanvas = viewable.getMainCanvas();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            g = mainCanvas.createCanvasGraphics();
            temporalCanvas.setSize(mainCanvas.getWidth(), mainCanvas.getHeight());
            mainCanvas.add(temporalCanvas);
            x1 = e.getX();
            y1 = e.getY();
            painter.setFileSaved(false);
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
            g.dispose();
            mainCanvas.repaint();
            window.setTitle(painter.getFileName());
            LOGGER.info("Нарисован объект");
            LOGGER.debug("Инструментом: "  + painter.getDrawingInstrument() +"\nЦветом: " + painter.getInstrumentColor() + "\nВ файл: " + painter.getFileName());
        }
    }
}
