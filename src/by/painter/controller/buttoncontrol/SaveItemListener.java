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

public class SaveItemListener extends ButtonController {

    public SaveItemListener(Viewable window) {
        super.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("Изображение с поддержкой прозрачности (png)", "png");
        chooser.addChoosableFileFilter(pngFilter);
        PaintCanvas canvas = window.getMainCanvas();
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileNameExtensionFilter chosenFilter = (FileNameExtensionFilter) chooser.getFileFilter();
            String[] extensions = chosenFilter.getExtensions();
            File f = new File(chooser.getSelectedFile().getAbsolutePath() + "." + extensions[0]);
            if (f.exists()) {
                if (window.showConfirmDialog("Такой файл уже существует. Перезаписать?", "Внимание!") == 0) {
                    try {
                        ImageIO.write(canvas.getOffscreen(), extensions[0], f);
                    } catch (IOException ex) {
                        window.showError(ex.getMessage());
                    }
                }
            } else {
                try {
                    f.createNewFile();
                } catch (IOException ex) {
                    window.showError(ex.getMessage());
                }
                try {
                    ImageIO.write(canvas.getOffscreen(), extensions[0], f);
                } catch (IOException ex) {
                    window.showError(ex.getMessage());
                }
            }
        }
    }
}
