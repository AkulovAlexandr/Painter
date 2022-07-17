package by.painter.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import by.painter.view.PaintCanva;
import by.painter.view.TemporalCanva;
import by.painter.view.Window;

public class RectangleDrawer implements DrawingInstrument {

	private int x1, y1, x2, y2;
	private Window window;
	private PaintCanva mainCanva;
	private TemporalCanva temporalCanva;
	private Graphics g;
	private RectangleAdapter rectAdapter;

	public RectangleDrawer(Window w) {
		window = w;
		mainCanva = window.getMainCanva();
		g = mainCanva.createCanvasGraphics();
		temporalCanva = new TemporalCanva(this);
		rectAdapter = new RectangleAdapter();
	}

	@Override
	public void setUpDrawMethod() {
		if (mainCanva.getMouseListeners().length > 0) {
			mainCanva.removeMouseListener(mainCanva.getMouseListeners()[0]);
			mainCanva.removeMouseMotionListener(mainCanva.getMouseMotionListeners()[0]);
		}
		mainCanva.addMouseListener(rectAdapter);
		mainCanva.addMouseMotionListener(rectAdapter);
		mainCanva.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
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
			temporalCanva.setBounds(mainCanva.getX() - 20, mainCanva.getY() - 23, mainCanva.getWidth(),
					mainCanva.getHeight());
			mainCanva.add(temporalCanva);
			x1 = e.getX();
			y1 = e.getY();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			temporalCanva.repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mainCanva.remove(temporalCanva);
			x2 = e.getX();
			y2 = e.getY();
			drawFigure(g);
			mainCanva.repaint();
		}
	}
}
