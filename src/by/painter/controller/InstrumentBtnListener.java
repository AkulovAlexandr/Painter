package by.painter.controller;

import java.awt.event.ActionEvent;
import by.painter.model.Instrument;

public class InstrumentBtnListener extends CommonController {

    private final Instrument instrument;

    public InstrumentBtnListener(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        painter.setDrawingInstrument(instrument);
    }

}
