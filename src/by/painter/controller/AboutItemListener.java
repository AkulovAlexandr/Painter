package by.painter.controller;

import by.painter.view.userinterface.Viewable;
import by.painter.view.userinterface.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutItemListener extends CommonController {

    public AboutItemListener(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = "\"" + Window.DEFAULT_TITLE_VERSION + "\"\n" +
                "Учебный проект курса по \"Java\".\n" +
                "GitHub: https://github.com/AkulovAlexandr/Painter\n" +
                "Автор: Акулов Александр\n" +
                "IT-Академия \"ШАГ\" г.Бобруйск, 2022г.";
        JOptionPane.showMessageDialog((Component) window, text, "О программе", JOptionPane.INFORMATION_MESSAGE);
    }
}
