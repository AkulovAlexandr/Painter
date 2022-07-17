package by.painter.controller.buttoncontrol;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import by.painter.view.PaintCanva;
import by.painter.view.Window;

public class ClearBtnListener implements ButtonController {

	private Window window;
	private PaintCanva mainCanva;

	public ClearBtnListener(Window window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainCanva = window.getMainCanva();
		mainCanva.cleanCanvas();
		mainCanva.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if (mainCanva.getMouseListeners().length > 0) {
			mainCanva.removeMouseListener(mainCanva.getMouseListeners()[0]);
			mainCanva.removeMouseMotionListener(mainCanva.getMouseMotionListeners()[0]);
		}
	}

}
