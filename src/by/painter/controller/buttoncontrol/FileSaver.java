package by.painter.controller.buttoncontrol;

import by.painter.view.PaintCanvas;
import by.painter.view.Viewable;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.io.*;

public class FileSaver extends ButtonController {

    public FileSaver(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintCanvas canvas = window.getMainCanvas();
        File f = new File("picture.png");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            ImageIO.write(canvas.getOffscreen(), "png", f);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
