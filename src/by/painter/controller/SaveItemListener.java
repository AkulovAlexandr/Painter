package by.painter.controller;

import by.painter.view.userinterface.Viewable;

import java.awt.event.ActionEvent;

public class SaveItemListener extends CommonController {

    public SaveItemListener(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.saveImage();
    }
}
