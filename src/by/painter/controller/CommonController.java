package by.painter.controller;

import by.painter.model.Painter;
import by.painter.view.userinterface.Viewable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CommonController implements ActionListener {

    protected Painter model;
    protected Viewable window;

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException();
    }

}
