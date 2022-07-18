package by.painter.controller.buttoncontrol;

import by.painter.model.Painter;
import by.painter.view.Viewable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ButtonController implements ActionListener {
    protected Painter model;
    protected Viewable window;

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException();
    }

}
