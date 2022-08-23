package by.painter.controller;

import by.painter.view.userinterface.Viewable;

import java.awt.event.ActionEvent;

public class OpenItemListener extends CommonController {

    public OpenItemListener(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.loadImage();
    }
}
