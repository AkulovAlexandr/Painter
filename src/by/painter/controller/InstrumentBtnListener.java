package by.painter.controller;

import java.awt.event.ActionEvent;

import by.painter.model.Instrument;
import by.painter.model.Painter;

public class InstrumentBtnListener extends CommonController {

    private final Instrument instrument;

    public InstrumentBtnListener(Painter model, Instrument instrument) {
        super.model = model;
        this.instrument = instrument;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setMainInstrument(instrument);
    }

}
