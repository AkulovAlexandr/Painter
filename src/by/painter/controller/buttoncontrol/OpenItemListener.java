package by.painter.controller.buttoncontrol;

import by.painter.controller.ButtonController;
import by.painter.view.PaintCanvas;
import by.painter.view.Viewable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class OpenItemListener extends ButtonController {

    public OpenItemListener(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Изображение с поддержкой прозрачности (png)", "png");
        chooser.addChoosableFileFilter(filter);
        PaintCanvas canvas = window.getMainCanvas();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                canvas.setOffscreen(ImageIO.read(f));
                canvas.repaint();
            } catch (IOException ex) {
                window.showError(ex.getMessage());
            }
        }
    }
}
