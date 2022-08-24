package by.painter.model.instrument.filledinstrument;

import by.painter.model.instrument.DrawingInstrument;
import by.painter.view.paintlayer.PaintCanvas;
import by.painter.view.userinterface.Viewable;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Filler extends DrawingInstrument {

    protected int x1, y1;
    protected Graphics g;

    public Filler(Viewable w) {
        super(w);
        super.adapter = new FillerAdapter(w);
    }

    @Override
    public void drawFigure(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(painter.getInstrumentColor());

    }

    private class FillerAdapter extends MouseAdapter {
        private final PaintCanvas mainCanvas;

        private FillerAdapter(Viewable viewable) {
            mainCanvas = viewable.getMainCanvas();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            g = mainCanvas.createCanvasGraphics();
            x1 = e.getX();
            y1 = e.getY();
            mainCanvas.repaint();
        }
    }
}
