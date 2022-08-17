package by.painter.controller.buttoncontrol;

import java.awt.event.ActionEvent;

import by.painter.controller.ButtonController;
import by.painter.model.Instrument;
import by.painter.model.Painter;

public class InstrumentBtnListener extends ButtonController {

    private final Instrument instrument;

    public InstrumentBtnListener(Painter model, Instrument instrument) {
        super.model = model;
        this.instrument = instrument;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setMainInstrument(instrument);
        model.getMainInstrument().setUpDrawMethod();
    }

}
