package by.painter.view;

import by.painter.model.Painter;

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
}
