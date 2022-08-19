package by.painter.controller.buttoncontrol;

import by.painter.view.Viewable;

import java.awt.event.ActionEvent;

public class OpenItemListener extends ButtonController {

    public OpenItemListener(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.loadImage();
    }
}
