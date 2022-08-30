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

    public static final String DEFAULT_TITLE_VERSION = "🌢 Painter (v0.9)";
    private Painter painter;
    private JPanel toolsPanel;
    private PaintCanvas mainCanvas;
    private JToolBar colorPreview;
    private ImageLoadable imageLoader;
    private JCheckBoxMenuItem darkTheme;
    private JCheckBoxMenuItem lightTheme;
    private Font defaultFont;
    private final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    public Window(Painter painter) {
        this.painter = painter;
        loadResources();
        initElements();
    }

    private void loadResources() {
        try {
            Image windowIcon = ImageIO.read(Objects.requireNonNull(Window.class.getResourceAsStream("/images/window_icon.png")));
            setIconImage(windowIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(java.awt.Window.class.getResourceAsStream("/fonts/LiberationSans-Bold.ttf")));
            ge.registerFont(defaultFont);
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initElements() {
        initToolsPanel();
        initMenuBar();

        imageLoader = new ImageLoader(this);
        mainCanvas = new PaintCanvas();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(mainCanvas);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(toolsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane)
                                .addContainerGap())
        );

        setLocation(400, 200);
        setMinimumSize(new Dimension(800, 700));
        setTitle(DEFAULT_TITLE_VERSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new CloseBtnListener(this));
        pack();
        setVisible(true);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu();
        JMenuItem fileOpen = new JMenuItem();
        JMenuItem fileSave = new JMenuItem();
        JMenu menuView = new JMenu();
        JMenu viewTheme = new JMenu();
        JMenu menuHelp = new JMenu();
        JMenuItem aboutItem = new JMenuItem();

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

        darkTheme = new JCheckBoxMenuItem();
        lightTheme = new JCheckBoxMenuItem();
        ActionListener themeItemListener = new ThemeItemListener(this);
        darkTheme.setText(Theme.DARK_THEME.getTitle());
        darkTheme.setCursor(handCursor);
        darkTheme.addActionListener(themeItemListener);
        darkTheme.setSelected(true);

        lightTheme.setText(Theme.LIGHT_THEME.getTitle());
        lightTheme.setCursor(handCursor);
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
    }

    private void initToolsPanel() {
        toolsPanel = new JPanel();
        colorPreview = new JToolBar();
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
        JButton lineBtn = new JButton();
        JButton triangleBtn = new JButton();
        JButton clearBtn = new JButton();
        GridBagConstraints gridBagConstraints;

        toolsPanel.setLayout(new GridBagLayout());

        panelName.setText("Инструменты");
        panelName.setFont(defaultFont.deriveFont(Font.PLAIN, 14f));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(25, 26, 0, 24);
        toolsPanel.add(panelName, gridBagConstraints);

        penBtn.setText("✎");
        penBtn.setCursor(handCursor);
        penBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.PEN));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 30, 0, 0);
        toolsPanel.add(penBtn, gridBagConstraints);

        paintbrushBtn.setText("🖌");
        paintbrushBtn.setCursor(handCursor);
        paintbrushBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.PAINTBRUSH));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(paintbrushBtn, gridBagConstraints);

        lineBtn.setText("━");
        lineBtn.setCursor(handCursor);
        lineBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.LINE));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        toolsPanel.add(lineBtn, gridBagConstraints);

        clearBtn.setText("⏏");
        clearBtn.setCursor(handCursor);
        clearBtn.addActionListener(new ClearBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(clearBtn, gridBagConstraints);

        rectangleBtn.setText("□");
        rectangleBtn.setFont(defaultFont.deriveFont(24f));
        rectangleBtn.setCursor(handCursor);
        rectangleBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.RECTANGLE));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 30, 0, 0);
        toolsPanel.add(rectangleBtn, gridBagConstraints);

        rectangleFilledBtn.setText("■");
        rectangleFilledBtn.setFont(defaultFont.deriveFont(32f));
        rectangleFilledBtn.setCursor(handCursor);
        rectangleFilledBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.FILL_RECTANGLE));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = -8;
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(rectangleFilledBtn, gridBagConstraints);

        circleBtn.setText("◯");
        circleBtn.setCursor(handCursor);
        circleBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.CIRCLE));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 30, 0, 0);
        toolsPanel.add(circleBtn, gridBagConstraints);

        circleFilledBtn.setText("⬤");
        circleFilledBtn.setCursor(handCursor);
        circleFilledBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.FILL_CIRCLE));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(circleFilledBtn, gridBagConstraints);

        triangleBtn.setText("△");
        triangleBtn.setCursor(handCursor);
        triangleBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.TRIANGLE));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 30, 0, 0);
        toolsPanel.add(triangleBtn, gridBagConstraints);

        triangleFilledBtn.setText("▲");
        triangleFilledBtn.setFont(defaultFont.deriveFont(24f));
        triangleFilledBtn.setCursor(handCursor);
        triangleFilledBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.FILL_TRIANGLE));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(triangleFilledBtn, gridBagConstraints);

        colorChooserName.setText("Палитра");
        colorChooserName.setFont(defaultFont.deriveFont(Font.PLAIN, 14f));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(20, 43, 0, 0);
        toolsPanel.add(colorChooserName, gridBagConstraints);

        colorPreview.setBackground(Color.BLACK);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 31, 0, 0);
        toolsPanel.add(colorPreview, gridBagConstraints);

        blackBtn.setBackground(Color.BLACK);
        blackBtn.setCursor(handCursor);
        blackBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 35, 0, 0);
        toolsPanel.add(blackBtn, gridBagConstraints);

        whiteBtn.setBackground(Color.WHITE);
        whiteBtn.setCursor(handCursor);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(whiteBtn, gridBagConstraints);

        redBtn.setBackground(Color.RED);
        redBtn.setCursor(handCursor);
        redBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(redBtn, gridBagConstraints);

        grayBtn.setBackground(Color.GRAY);
        grayBtn.setCursor(handCursor);
        grayBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 35, 0, 0);
        toolsPanel.add(grayBtn, gridBagConstraints);

        yellowBtn.setBackground(Color.YELLOW);
        yellowBtn.setCursor(handCursor);
        yellowBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(yellowBtn, gridBagConstraints);

        orangeBtn.setBackground(Color.ORANGE);
        orangeBtn.setCursor(handCursor);
        orangeBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 35, 0, 0);
        toolsPanel.add(orangeBtn, gridBagConstraints);

        cyanBtn.setBackground(Color.CYAN);
        cyanBtn.setCursor(handCursor);
        cyanBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(cyanBtn, gridBagConstraints);

        greenBtn.setBackground(Color.GREEN);
        greenBtn.setCursor(handCursor);
        greenBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 35, 0, 0);
        toolsPanel.add(greenBtn, gridBagConstraints);

        magnetaBtn.setBackground(Color.MAGENTA);
        magnetaBtn.setCursor(handCursor);
        magnetaBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        toolsPanel.add(magnetaBtn, gridBagConstraints);

        blueBtn.setBackground(Color.BLUE);
        blueBtn.setCursor(handCursor);
        blueBtn.addActionListener(new ColorBtnListener(this));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = -47;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 35, 64, 0);
        toolsPanel.add(blueBtn, gridBagConstraints);
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

    @Override
    public JCheckBoxMenuItem getDarkTheme() {
        return darkTheme;
    }

    @Override
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
