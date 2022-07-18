package by.painter.view;

import by.painter.model.Painter;

import javax.swing.*;

public interface Viewable {
    void initElements(String name);

    PaintCanvas getMainCanvas();

    void setPainter(Painter painter);

    void setVisible(boolean b);
}
