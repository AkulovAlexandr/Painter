package by.painter.view.userinterface;

import by.painter.model.Painter;
import by.painter.view.paintlayer.PaintCanvas;

import javax.swing.*;

public interface Viewable {
    void initElements(String name);

    JToolBar getColorPreview();

    PaintCanvas getMainCanvas();

    Painter getPainter();

    void setPainter(Painter painter);

    void showError(String message);

    int showConfirmDialog(String message, String title);

    void saveImage();

    void loadImage();

    void update();
}
