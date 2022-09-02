package by.painter.model.instrument;

import by.painter.model.Painter;
import by.painter.view.userinterface.Viewable;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class DrawingInstrument {

    protected final static Logger LOGGER = Logger.getLogger("log");
    protected Viewable window;
    protected final Painter painter = Painter.getInstance();
    protected MouseAdapter adapter;

    protected DrawingInstrument(Viewable window){
        this.window = window;
    }

    public void drawFigure(Graphics g) {
    }

    public MouseAdapter getMouseAdapter() {
        return adapter;
    }
}
