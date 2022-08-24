package by.painter.view.userinterface;

import by.painter.controller.*;
import by.painter.model.Instrument;
import by.painter.model.Painter;
import by.painter.view.paintlayer.PaintCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class Window extends JFrame implements Viewable {

    public static final String DEFAULT_TITLE_VERSION = "Рисовальщик (v0.8)";
    private Painter painter;
    private PaintCanvas mainCanvas;
    private JToolBar colorPreview;
    private ImageLoadable imageLoader;
    private JCheckBoxMenuItem darkTheme;
    private JCheckBoxMenuItem lightTheme;
    private Font defaultFont;

    public Window(Painter painter) {
        this.painter = painter;
        try {
            Image windowIcon = ImageIO.read(Objects.requireNonNull(Window.class.getResourceAsStream("/images/window_icon.png")));
            setIconImage(windowIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            defaultFont =  Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Window.class.getResourceAsStream("/fonts/LiberationSans-Bold.ttf")));
            ge.registerFont(defaultFont);
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }
        initElements();
    }

    @Override
    public void initElements() {
        super.setLocationRelativeTo(null);
        super.setMinimumSize(new Dimension(1024, 768));
        super.setTitle(DEFAULT_TITLE_VERSION);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.addWindowListener(new CloseBtnListener(this));
        imageLoader = new ImageLoader(this);
        mainCanvas = new PaintCanvas();
        colorPreview = new JToolBar();
        darkTheme = new JCheckBoxMenuItem();
        lightTheme = new JCheckBoxMenuItem();
        JScrollPane scrollPane = new JScrollPane();
        JPanel toolsPanel = new JPanel();
        JLabel panelName = new JLabel();
        JLabel colorChooserName = new JLabel();
        JButton penBtn = new JButton();
        JButton paintbrushBtn = new JButton();
        JButton blackBtn = new JButton();
        JButton triangleFilledBtn = new JButton();
        JButton whiteBtn = new JButton();
        JButton redBtn = new JButton();
        JButton grayBtn = new JButton();
        JButton yellowBtn = new JButton();
        JButton orangeBtn = new JButton();
        JButton cyanBtn = new JButton();
        JButton greenBtn = new JButton();
        JButton magnetaBtn = new JButton();
        JButton blueBtn = new JButton();
        JButton rectangleFilledBtn = new JButton();
        JButton circleBtn = new JButton();
        JButton circleFilledBtn = new JButton();
        JButton rectangleBtn = new JButton();
        JButton fillBtn = new JButton();
        JButton lineBtn = new JButton();
        JButton triangleBtn = new JButton();
        JButton textBtn = new JButton();
        JButton clearBtn = new JButton();
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu();
        JMenuItem fileOpen = new JMenuItem();
        JMenuItem fileSave = new JMenuItem();
        JMenu menuView = new JMenu();
        JMenu viewTheme = new JMenu();
        JMenu menuHelp = new JMenu();
        JMenuItem aboutItem = new JMenuItem();
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        GroupLayout mainCanvasLayout = new GroupLayout(mainCanvas);
        mainCanvas.setLayout(mainCanvasLayout);
        mainCanvasLayout.setHorizontalGroup(
                mainCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 800, Short.MAX_VALUE)
        );
        mainCanvasLayout.setVerticalGroup(
                mainCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE)
        );
        scrollPane.setViewportView(mainCanvas);

        panelName.setText("Инструменты");
        panelName.setFont(defaultFont.deriveFont(Font.PLAIN,14f));
        colorChooserName.setText("Палитра");
        colorChooserName.setFont(defaultFont.deriveFont(Font.PLAIN,14f));

        penBtn.setText("✎");
        penBtn.setCursor(handCursor);
        penBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.PEN));

        paintbrushBtn.setText("🖌");
        paintbrushBtn.setCursor(handCursor);
        paintbrushBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.PAINTBRUSH));

        lineBtn.setText("━");
        lineBtn.setCursor(handCursor);
        lineBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.LINE));

        fillBtn.setText("🌢");
        fillBtn.setEnabled(false);
        fillBtn.setCursor(handCursor);
        fillBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.FILLER));

        rectangleBtn.setFont(defaultFont.deriveFont(24f));
        rectangleBtn.setText("□");
        rectangleBtn.setCursor(handCursor);
        rectangleBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.RECTANGLE));

        rectangleFilledBtn.setFont(defaultFont.deriveFont(32f));
        rectangleFilledBtn.setText("■");
        rectangleFilledBtn.setCursor(handCursor);
        rectangleFilledBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.FILL_RECTANGLE));

        circleBtn.setText("◯");
        circleBtn.setCursor(handCursor);
        circleBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.CIRCLE));

        circleFilledBtn.setText("⬤");
        circleFilledBtn.setCursor(handCursor);
        circleFilledBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.FILL_CIRCLE));

        triangleBtn.setText("△");
        triangleBtn.setCursor(handCursor);
        triangleBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.TRIANGLE));

        triangleFilledBtn.setFont(new Font("Liberation Sans", Font.PLAIN, 24));
        triangleFilledBtn.setText("▲");
        triangleFilledBtn.setCursor(handCursor);
        triangleFilledBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.FILL_TRIANGLE));

        textBtn.setText("A");
        textBtn.setEnabled(false);
        textBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.TEXT));

        clearBtn.setText("⏏");
        clearBtn.setCursor(handCursor);
        clearBtn.addActionListener(new ClearBtnListener(this));

        colorPreview.setBackground(Color.black);

        blackBtn.setBackground(Color.BLACK);
        blackBtn.setCursor(handCursor);
        blackBtn.addActionListener(new ColorBtnListener(this));

        whiteBtn.setBackground(Color.WHITE);
        whiteBtn.setCursor(handCursor);
        whiteBtn.addActionListener(new ColorBtnListener(this));

        redBtn.setBackground(Color.red);
        redBtn.setCursor(handCursor);
        redBtn.addActionListener(new ColorBtnListener(this));

        grayBtn.setBackground(Color.gray);
        grayBtn.setCursor(handCursor);
        grayBtn.addActionListener(new ColorBtnListener(this));

        yellowBtn.setBackground(Color.yellow);
        yellowBtn.setCursor(handCursor);
        yellowBtn.addActionListener(new ColorBtnListener(this));

        orangeBtn.setBackground(Color.orange);
        orangeBtn.setCursor(handCursor);
        orangeBtn.addActionListener(new ColorBtnListener(this));

        cyanBtn.setBackground(Color.cyan);
        cyanBtn.setCursor(handCursor);
        cyanBtn.addActionListener(new ColorBtnListener(this));

        greenBtn.setBackground(Color.green);
        greenBtn.setCursor(handCursor);
        greenBtn.addActionListener(new ColorBtnListener(this));

        magnetaBtn.setBackground(Color.magenta);
        magnetaBtn.setCursor(handCursor);
        magnetaBtn.addActionListener(new ColorBtnListener(this));

        blueBtn.setBackground(Color.blue);
        blueBtn.setCursor(handCursor);
        blueBtn.addActionListener(new ColorBtnListener(this));

        GroupLayout toolsPanelLayout = new GroupLayout(toolsPanel);
        toolsPanel.setLayout(toolsPanelLayout);
        toolsPanelLayout.setHorizontalGroup(
                toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                                                                .addComponent(penBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(paintbrushBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(colorPreview, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                                                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(circleBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(rectangleBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(lineBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(triangleBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(textBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(circleFilledBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(triangleFilledBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(rectangleFilledBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(fillBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                                                                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                        .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(greenBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(blueBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(orangeBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                                                                        .addComponent(grayBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(yellowBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(redBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(cyanBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(magnetaBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                                                                                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                                                                                .addComponent(blackBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(whiteBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))))
                                                        .addComponent(panelName)))
                                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(colorChooserName)))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        toolsPanelLayout.setVerticalGroup(
                toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(toolsPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(panelName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(penBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(paintbrushBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(fillBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lineBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(rectangleBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rectangleFilledBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(circleFilledBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(circleBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(triangleFilledBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(triangleBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(colorChooserName)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(colorPreview, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(blackBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(whiteBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(grayBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(redBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(orangeBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yellowBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(greenBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cyanBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(toolsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(blueBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(magnetaBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuFile.setText("Файл");
        menuFile.setCursor(handCursor);

        fileOpen.setText("Открыть");
        fileOpen.setCursor(handCursor);
        fileOpen.addActionListener(new OpenItemListener(this));

        fileSave.setText("Сохранить");
        fileSave.setCursor(handCursor);
        fileSave.addActionListener(new SaveItemListener(this));

        menuFile.add(fileOpen);
        menuFile.add(fileSave);

        menuView.setText("Вид");
        menuView.setCursor(handCursor);

        viewTheme.setText("Темы оформления");
        viewTheme.setCursor(handCursor);

        menuView.add(viewTheme);

        ActionListener themeItemListener = new ThemeItemListener(this);
        darkTheme.setText(Theme.DARK_THEME.getTitle());
        darkTheme.setCursor(handCursor);
        darkTheme.addActionListener(themeItemListener);

        lightTheme.setText(Theme.LIGHT_THEME.getTitle());
        lightTheme.setCursor(handCursor);
        lightTheme.setSelected(true);
        lightTheme.addActionListener(themeItemListener);

        viewTheme.add(darkTheme);
        viewTheme.add(lightTheme);

        menuHelp.setText("Помощь");
        menuHelp.setCursor(handCursor);

        aboutItem.setText("О программе");
        aboutItem.setCursor(handCursor);
        aboutItem.addActionListener(new AboutItemListener(this));

        menuHelp.add(aboutItem);

        menuBar.add(menuFile);
        menuBar.add(menuView);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(toolsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane)
                                .addContainerGap())
        );
        pack();
        setVisible(true);
    }

    @Override
    public void saveImage() {
        imageLoader.save();
    }

    @Override
    public void loadImage() {
        imageLoader.load();
    }

    @Override
    public void setTitle(String title) {
        if (!painter.isFileSaved()) {
            super.setTitle(title + " *");
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public JToolBar getColorPreview() {
        return colorPreview;
    }

    @Override
    public PaintCanvas getMainCanvas() {
        return mainCanvas;
    }

    @Override
    public Painter getPainter() {
        return painter;
    }

    @Override
    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public JCheckBoxMenuItem getDarkTheme() {
        return darkTheme;
    }

    public JCheckBoxMenuItem getLightTheme() {
        return lightTheme;
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public int showDialog(String message, String title) {
        String[] options = {"Да", "Нет"};
        return JOptionPane.showOptionDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
    }
}
