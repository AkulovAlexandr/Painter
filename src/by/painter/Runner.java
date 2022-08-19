package by.painter;

import by.painter.model.Painter;
import by.painter.view.Viewable;
import by.painter.view.Window;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Runner {

    public static void main(String[] args) {
        initializeLookAndFeel();
        Painter mainProgram = new Painter();
        Viewable window = new Window(mainProgram);
        mainProgram.setWindow(window);
        mainProgram.start();
    }

    private static void initializeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

}
