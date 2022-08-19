package by.painter.view;

import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class PaintCanvas extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage offscreen = null;
    private int currentWidth = 0;
    private int currentHeight = 0;

    public PaintCanvas() {
        addComponentListener(new SizeTracker());
        setBackground(new Color(255, 255, 255));
    }

    public BufferedImage getOffscreen() {
        return offscreen;
    }

    public void setOffscreen(BufferedImage offscreen) {
        this.offscreen = offscreen;
    }

    public void extendCanvasTo(int newWidth, int newHeight) {
        int oldW = currentWidth;
        int oldH = currentHeight;
        int w = Math.max(newWidth, getWidth());
        int h = Math.max(newHeight, getHeight());
        currentWidth = Math.max(currentWidth, w);
        currentHeight = Math.max(currentHeight, h);
        if (currentWidth > oldW || currentHeight > oldH || offscreen == null) {
            BufferedImage old = offscreen;
            offscreen = new BufferedImage(currentWidth, currentHeight, BufferedImage.TYPE_INT_ARGB);
            if (old != null) {
                Graphics2D g = offscreen.createGraphics();
                g.drawImage(old, 0, 0, this);
                g.dispose();
            }
        }
    }

    public Graphics2D createCanvasGraphics() {
        return (offscreen == null) ? null : offscreen.createGraphics();
    }

    public void cleanCanvas() {
        offscreen = null;
        extendCanvasTo(currentWidth, currentHeight);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (offscreen != null) {
            Border b = this.getBorder();
            Insets i = (b == null) ? null : b.getBorderInsets(this);
            Graphics copy = (i == null) ? g.create()
                    : g.create(i.left, i.top, getWidth() - i.left - i.right, getHeight() - i.top - i.bottom);
            copy.drawImage(offscreen, 0, 0, this);
            copy.dispose();
        }
    }

    private class SizeTracker extends ComponentAdapter {

        @Override
        public void componentShown(ComponentEvent e) {
            e.getComponent().setPreferredSize(new Dimension(currentWidth, currentHeight));
            if (offscreen == null) {
                extendCanvasTo(getWidth(), getHeight());
            } else {
                extendCanvasTo(offscreen.getWidth(), offscreen.getHeight());
            }
        }

        @Override
        public void componentResized(ComponentEvent e) {
            e.getComponent().setPreferredSize(new Dimension(currentWidth, currentHeight));
            if (offscreen == null) {
                extendCanvasTo(getWidth(), getHeight());
            } else {
                extendCanvasTo(offscreen.getWidth(), offscreen.getHeight());
            }
        }
    }

    public void setCursor(Cursor cursor) {
        super.setCursor(cursor);
    }
}
