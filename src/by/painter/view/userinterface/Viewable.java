package by.painter.view.userinterface;

import by.painter.view.paintlayer.PaintCanvas;
import javax.swing.*;

public interface Viewable {

    JToolBar getColorPreview();

    PaintCanvas getMainCanvas();

    void showError(String message);

    int showDialog(String message, String title);

    void saveImage();

    void loadImage();

    void setTitle(String title);

    void repaint();

    JCheckBoxMenuItem getLightTheme();

    JCheckBoxMenuItem getDarkTheme();
}
