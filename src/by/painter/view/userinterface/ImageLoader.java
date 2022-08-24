package by.painter.view.userinterface;

import by.painter.model.Painter;
import by.painter.view.paintlayer.PaintCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.YES_OPTION;

public class ImageLoader extends JFileChooser implements ImageLoadable {

    private final Viewable window;
    private final Painter painter;

    public ImageLoader(Viewable window) {
        this.window = window;
        this.painter = window.getPainter();
        localizeElements();
    }

    private void localizeElements() {
        setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("Изображение PNG (с поддержкой прозрачности)", "png");
        addChoosableFileFilter(pngFilter);
        UIManager.put("FileChooser.openDialogTitleText", "Открыть");
        UIManager.put("FileChooser.lookInLabelText", "Путь");
        UIManager.put("FileChooser.saveInLabelText", "Путь");
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.directoryOpenButtonText", "Открыть");
        UIManager.put("FileChooser.directoryOpenButtonToolTipText", "Открыть папку");
        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.fileNameLabelText", "Имя файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Тип файла");
        UIManager.put("FileChooser.openButtonToolTipText", "Открыть выбранный файл");
        UIManager.put("FileChooser.saveButtonToolTipText", "Сохранить выбранный файл");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Вернуться на главный экран");
        UIManager.put("FileChooser.fileNameHeaderText", "Имя файла");
        UIManager.put("FileChooser.upFolderToolTipText", "На уровень выше");
        UIManager.put("FileChooser.homeFolderToolTipText", "Домой");
        UIManager.put("FileChooser.newFolderToolTipText", "Создать новую папку");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Список");
        UIManager.put("FileChooser.newFolderButtonText", "Создать новую папку");
        UIManager.put("FileChooser.renameFileButtonText", "Переименовать");
        UIManager.put("FileChooser.deleteFileButtonText", "Удалить");
        UIManager.put("FileChooser.filterLabelText", "Тип файла");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Подробный список");
        UIManager.put("FileChooser.fileSizeHeaderText", "Размер");
        UIManager.put("FileChooser.fileDateHeaderText", "Дата изменения");
        setApproveButtonText("Открыть");
        setPreferredSize(new Dimension(600, 400));
        updateUI();
    }

    @Override
    public void save() {
        setDialogTitle("Сохранить файл");
        setSelectedFile(new File(painter.getFileName()));
        PaintCanvas canvas = window.getMainCanvas();
        if (showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileNameExtensionFilter chosenFilter = (FileNameExtensionFilter) getFileFilter();
            String[] extensions = chosenFilter.getExtensions();
            File file = getSelectedFile();
            String fileName = file.getName();
            if (file.exists()) {
                String message = "Текущие изменения будут утеряны.\nПродолжить?";
                String title = "Файл не был сохранен!";
                if (window.showDialog(message, title) == YES_OPTION) {
                    try {
                        ImageIO.write(canvas.getOffscreen(), extensions[0], file);
                        painter.setFileSaved(true);
                        painter.setFileName(fileName);
                        window.setTitle(fileName);
                    } catch (IOException ex) {
                        window.showError(ex.getMessage());
                    }
                }
            } else {
                try {
                    file.createNewFile();
                    ImageIO.write(canvas.getOffscreen(), extensions[0], file);
                    painter.setFileSaved(true);
                    painter.setFileName(fileName);
                    window.setTitle(fileName);
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
            String message = "Текущие изменения будут утеряны.\nПродолжить?";
            String title = "Файл не был сохранен!";
            if (painter.isFileSaved() || window.showDialog(message, title) == YES_OPTION) {
                File selectedFile = getSelectedFile();
                String fileName = selectedFile.getName();
                try {
                    BufferedImage img = ImageIO.read(selectedFile);
                    canvas.setSize(new Dimension(img.getWidth(), img.getHeight()));
                    canvas.setOffscreen(img);
                    painter.setFileSaved(true);
                    painter.setFileName(fileName);
                    window.setTitle((fileName));
                    window.repaint();
                } catch (IOException ex) {
                    window.showError(ex.getMessage());
                }
            }
        }
    }
}
