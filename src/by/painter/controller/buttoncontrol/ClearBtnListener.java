package by.painter.controller.buttoncontrol;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import by.painter.view.PaintCanvas;
import by.painter.view.Window;

public class ClearBtnListener implements ButtonController {

	private final Window window;

	public ClearBtnListener(Window window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PaintCanvas mainCanvas = window.getMainCanvas();
		mainCanvas.cleanCanvas();
		mainCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if (mainCanvas.getMouseListeners().length > 0) {
			mainCanvas.removeMouseListener(mainCanvas.getMouseListeners()[0]);
			mainCanvas.removeMouseMotionListener(mainCanvas.getMouseMotionListeners()[0]);
		}
	}

}
