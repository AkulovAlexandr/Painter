package by.painter.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class LocaledFileChooser extends JFileChooser implements FileChooser {

    private final Viewable window;

    public LocaledFileChooser(Viewable window) {
        this.window = window;
        localizeElements();
    }

    private void localizeElements() {
        setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("Изображение с поддержкой прозрачности (png)", "png");
        addChoosableFileFilter(pngFilter);
    }

    @Override
    public void save() {
        PaintCanvas canvas = window.getMainCanvas();
        if (showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileNameExtensionFilter chosenFilter = (FileNameExtensionFilter) getFileFilter();
            String[] extensions = chosenFilter.getExtensions();
            File f = new File(getSelectedFile().getAbsolutePath() + "." + extensions[0]);
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

    @Override
    public void load() {
        PaintCanvas canvas = window.getMainCanvas();
        if (showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = getSelectedFile();
            try {
                canvas.setOffscreen(ImageIO.read(f));
                canvas.repaint();
            } catch (IOException ex) {
                window.showError(ex.getMessage());
            }
        }
    }
}
