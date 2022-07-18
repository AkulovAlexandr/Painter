package by.painter.controller.buttoncontrol;

import java.awt.event.ActionEvent;
import by.painter.model.Instrument;
import by.painter.model.Painter;

public class InstrumentBtnListener implements ButtonController {

	private final Painter model;
	private final Instrument instrument;

	public InstrumentBtnListener(Painter model, Instrument instrument) {
		this.model = model;
		this.instrument = instrument;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setMainInstrument(instrument);
		model.getMainInstrument().setUpDrawMethod();
	}

}
