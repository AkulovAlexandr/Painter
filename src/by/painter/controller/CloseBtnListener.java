package by.painter.controller;

import by.painter.model.Painter;
import by.painter.view.userinterface.Viewable;
import org.apache.log4j.Logger;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.YES_OPTION;

public class CloseBtnListener extends WindowAdapter {

    private final static Logger LOGGER = Logger.getLogger("log");
    private final Viewable window;

    public CloseBtnListener(Viewable window) {
        this.window = window;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        LOGGER.info("Закрытие программы...");
        if (!Painter.getInstance().isFileSaved()) {
            String message = "Текущие изменения будут утеряны.\nСохранить файл перед закрытием?";
            String title = "Файл не был сохранен!";
            if (window.showDialog(message, title) == YES_OPTION) {
                window.saveImage();
            }
        }
    }
}
