package by.painter.controller.buttoncontrol;

import by.painter.view.Viewable;

import java.awt.event.ActionEvent;

public class SaveItemListener extends ButtonController {

    public SaveItemListener(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.saveImage();
    }
}
