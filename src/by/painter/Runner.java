package by.painter;

import by.painter.model.Painter;
import by.painter.view.Viewable;
import by.painter.view.Window;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Runner {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        Painter mainProgram = new Painter();
        Viewable gui = new Window(mainProgram);
        mainProgram.start(gui);
    }

}
