package by.painter.controller.buttoncontrol;

import java.awt.event.ActionEvent;
import by.painter.model.Instruments;
import by.painter.model.Painter;

public class InstrumentBtnListener implements ButtonController {

	private Painter model;
	private Instruments instrument;

	public InstrumentBtnListener(Painter model, Instruments instrument) {
		this.model = model;
		this.instrument = instrument;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setMainInstrument(instrument);
	}

}
