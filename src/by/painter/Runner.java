package by.painter;

import by.painter.model.Painter;
import by.painter.view.Viewable;
import by.painter.view.Window;

public class Runner {

    public static void main(String[] args) {
        Painter mainProgram = new Painter();
        Viewable guiV1 = new Window(mainProgram);
        mainProgram.start(guiV1);
    }

}
