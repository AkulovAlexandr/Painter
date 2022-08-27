package by.painter;

import by.painter.model.Painter;
import by.painter.view.userinterface.Viewable;
import by.painter.view.userinterface.Window;
import com.formdev.flatlaf.intellijthemes.*;
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
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel(new FlatDraculaIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

}
