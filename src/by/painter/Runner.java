package by.painter;

import by.painter.model.Painter;
import by.painter.view.userinterface.Window;
import by.painter.view.userinterface.Viewable;
import com.formdev.flatlaf.intellijthemes.*;
import org.apache.log4j.Logger;

import javax.swing.*;

import java.util.Arrays;

import static java.lang.System.exit;

public class Runner {

    private final static Logger LOGGER = Logger.getLogger("log");

    public static void main(String[] args) {
        initializeLookAndFeel();
        LOGGER.info("Создание объектов...");
        Painter mainProgram = Painter.getInstance();
        Viewable Window = new Window();
        mainProgram.setWindow(Window);
        try {
            mainProgram.start();
        } catch (NullPointerException ex) {
            LOGGER.error("Визуальный интерфейс не установлен для выполняемой программы. Старт невозможен." + Arrays.toString(ex.getStackTrace()));
            JOptionPane.showMessageDialog(null, "Визуальный интерфейс не загружен.", "Критическая ошибка!", JOptionPane.ERROR_MESSAGE);
            exit(0);
        }
    }

    private static void initializeLookAndFeel() {
        LOGGER.info("Инициализация визаульного оформления...");
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel(new FlatDraculaIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            LOGGER.error("Неподдерживаемая тема оформления: " + UIManager.getLookAndFeel() + ex);
        }
    }

}
