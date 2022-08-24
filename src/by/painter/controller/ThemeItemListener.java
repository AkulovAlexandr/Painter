package by.painter.controller;

import by.painter.view.userinterface.Viewable;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static com.formdev.flatlaf.FlatLaf.updateUI;

public class ThemeItemListener extends CommonController {

    public ThemeItemListener(Viewable window) {
        super.window = window;
        super.model = window.getPainter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            updateUI();
        } catch (UnsupportedLookAndFeelException ex) {
            throw new RuntimeException(ex);
        }

    }
}
