package by.painter.view;

import by.painter.model.Painter;

import javax.swing.*;

public interface Viewable {
    void initElements(String name);

    PaintCanvas getMainCanvas();

    void setVisible(boolean b);

    void setPainter(Painter painter);
}
