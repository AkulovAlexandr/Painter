package by.painter.model.instrument;

import by.painter.model.Painter;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class DrawingInstrument {

    protected Painter painter;

    protected MouseAdapter adapter;

    public void drawFigure(Graphics g) {
    }

    public MouseAdapter getMouseAdapter() {
        return adapter;
    }
}
