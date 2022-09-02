package by.painter.controller;

import by.painter.view.userinterface.Viewable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorBtnListener extends CommonController {

    public ColorBtnListener(Viewable window) {
        super(window);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color clr = ((JButton) e.getSource()).getBackground();
        painter.setInstrumentColor(clr);
        window.getColorPreview().setBackground(clr);
    }
}
