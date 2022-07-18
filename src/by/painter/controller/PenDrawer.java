package by.painter.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import by.painter.view.PaintCanvas;
import by.painter.view.Viewable;

public class PenDrawer implements DrawingInstrument {

	private int x1, y1, x2, y2;
	private Graphics g;
	private PaintCanvas mainCanvas;
	private MouseAdapter penAdapter;

	public PenDrawer(){

	}
	public PenDrawer(Viewable w) {
		penAdapter = new PenAdapter();
		mainCanvas = w.getMainCanvas();
		g = mainCanvas.createCanvasGraphics();
	}

	@Override
	public void setUpDrawMethod() {
		if (mainCanvas.getMouseListeners().length > 0) {
			mainCanvas.removeMouseListener(mainCanvas.getMouseListeners()[0]);
			mainCanvas.removeMouseMotionListener(mainCanvas.getMouseMotionListeners()[0]);
		}
		mainCanvas.addMouseListener(penAdapter);
		mainCanvas.addMouseMotionListener(penAdapter);
		mainCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
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
			mainCanvas.repaint();
		}

	}

}
