package by.painter.model;

import by.painter.controller.DrawingInstrument;
import by.painter.controller.PenDrawer;
import by.painter.controller.RectangleDrawer;
import by.painter.view.Window;

public class Painter {

	private Window window;
	private DrawingInstrument mainInstrument;

	public Painter() {

	}

	public void start() {
		window = new Window(this);
	}

	public void setMainInstrument(Instruments instrument) {
		switch (instrument) {
		case PEN:
			mainInstrument = new PenDrawer(window);
			mainInstrument.setUpDrawMethod();
			break;
		case RECTANGLE:
			mainInstrument = new RectangleDrawer(window);
			mainInstrument.setUpDrawMethod();
			break;
		default:
			break;
		}
	}

	public DrawingInstrument getMainInstrument() {
		return mainInstrument;
	}

}
