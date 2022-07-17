package by.painter.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import by.painter.view.PaintCanva;
import by.painter.view.Window;

public class PenDrawer implements DrawingInstrument {

	private int x1, y1, x2, y2;
	private Window window;
	private Graphics g;
	private PaintCanva mainCanva;
	private PenAdapter penAdapter;

	public PenDrawer(Window w) {
		window = w;
		penAdapter = new PenAdapter();
		mainCanva = window.getMainCanva();
		g = mainCanva.createCanvasGraphics();
	}

	@Override
	public void setUpDrawMethod() {
		if (mainCanva.getMouseListeners().length > 0) {
			mainCanva.removeMouseListener(mainCanva.getMouseListeners()[0]);
			mainCanva.removeMouseMotionListener(mainCanva.getMouseMotionListeners()[0]);
		}
		mainCanva.addMouseListener(penAdapter);
		mainCanva.addMouseMotionListener(penAdapter);
		mainCanva.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	@Override
	public void drawFigure(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
		x2 = x1;
		y2 = y1;
	}

	private class PenAdapter extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
			x2 = x1;
			y2 = y1;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
			drawFigure(g);
			mainCanva.repaint();
		}

	}

}
