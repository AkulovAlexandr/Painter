package by.painter.controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import by.painter.view.paintlayer.PaintCanvas;
import by.painter.view.userinterface.Viewable;

public class ClearBtnListener extends CommonController {

    public ClearBtnListener(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintCanvas mainCanvas = window.getMainCanvas();
        mainCanvas.cleanCanvas();
        mainCanvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        if (mainCanvas.getMouseListeners().length > 0) {
            mainCanvas.removeMouseListener(mainCanvas.getMouseListeners()[0]);
            mainCanvas.removeMouseMotionListener(mainCanvas.getMouseMotionListeners()[0]);
        }
    }

}
