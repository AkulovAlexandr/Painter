package by.painter.controller;

import by.painter.view.userinterface.Theme;
import by.painter.view.userinterface.Viewable;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

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
        JCheckBoxMenuItem chosenCheckBox = (JCheckBoxMenuItem) e.getSource();
        Theme theme = Theme.fromString(chosenCheckBox.getText());
        try {
            switch (theme) {
                case DARK_THEME:
                    window.getLightTheme().setSelected(false);
                    chosenCheckBox.setSelected(true);
                    UIManager.setLookAndFeel(new FlatDraculaIJTheme());
                    updateUI();
                    LOGGER.info("Установлена темная тема");
                    break;
                case LIGHT_THEME:
                    window.getDarkTheme().setSelected(false);
                    chosenCheckBox.setSelected(true);
                    UIManager.setLookAndFeel(new FlatCyanLightIJTheme());
                    updateUI();
                    LOGGER.info("Установлена светлая тема");
                    break;
            }
        } catch (UnsupportedLookAndFeelException ex) {
            LOGGER.error("Неподдерживаемая тема оформления: " + UIManager.getLookAndFeel() + ex);
        }

    }
}
