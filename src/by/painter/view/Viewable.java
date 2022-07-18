package by.painter.view;

import by.painter.model.Painter;

public interface Viewable {
    void initElements(String name);

    PaintCanvas getMainCanvas();

    void setPainter(Painter painter);

    void setVisible(boolean b);
}
