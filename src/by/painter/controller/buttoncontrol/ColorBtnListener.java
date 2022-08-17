package by.painter.controller.buttoncontrol;

import by.painter.controller.ButtonController;
import by.painter.view.Viewable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorBtnListener extends ButtonController {

    public ColorBtnListener(Viewable window) {
        super.window = window;
        super.model = window.getPainter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color clr = ((JButton) e.getSource()).getBackground();
        model.setInstrumentColor(clr);
        window.getColorPreview().setBackground(clr);
    }
}
