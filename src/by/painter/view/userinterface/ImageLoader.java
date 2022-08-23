package by.painter.view.userinterface;

import by.painter.view.paintlayer.PaintCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader extends JFileChooser implements ImageLoadable {

    private final Viewable window;

    public ImageLoader(Viewable window) {
        this.window = window;
        localizeElements();
    }

    private void localizeElements() {
        setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("Изображение с поддержкой прозрачности (png)", "png");
        addChoosableFileFilter(pngFilter);
        setApproveButtonText("Подтвердить");

        setPreferredSize(new Dimension(800,600));
    }

    @Override
    public void save() {
        setDialogTitle("Сохранить файл");
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
        setDialogTitle("Открыть файл");
        PaintCanvas canvas = window.getMainCanvas();
        if (showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(f);
                canvas.setSize(new Dimension(img.getWidth(), img.getHeight()));
                canvas.setOffscreen(img);
                window.update();
            } catch (IOException ex) {
                window.showError(ex.getMessage());
            }
        }
    }
}
