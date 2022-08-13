package by.painter.view;

import by.painter.controller.buttoncontrol.ClearBtnListener;
import by.painter.controller.buttoncontrol.ColorBtnListener;
import by.painter.controller.buttoncontrol.FileSaver;
import by.painter.controller.buttoncontrol.InstrumentBtnListener;
import by.painter.model.Instrument;
import by.painter.model.Painter;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Viewable {

    private Painter painter;
    private PaintCanvas mainCanvas;
    private JToolBar colorPreview;

    public Window(Painter painter) {
        this.painter = painter;
        initElements("Painter 0.5 New Instruments");
    }

    @Override
    public void initElements(String name) {
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainCanvas = new PaintCanvas();
        colorPreview = new JToolBar();
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
        JMenuItem viewTheme = new JMenuItem();
        JMenuItem viewAbout = new JMenuItem();
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        GroupLayout mainCanvasLayout = new GroupLayout(mainCanvas);
        mainCanvas.setLayout(mainCanvasLayout);
        mainCanvasLayout.setHorizontalGroup(
                mainCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 770, Short.MAX_VALUE)
        );
        mainCanvasLayout.setVerticalGroup(
                mainCanvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 561, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(mainCanvas);

        panelName.setText("Инструменты");
        colorChooserName.setText("Палитра");

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

        rectangleBtn.setFont(new Font("Liberation Sans", Font.PLAIN, 24));
        rectangleBtn.setText("□");
        rectangleBtn.setCursor(handCursor);
        rectangleBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.RECTANGLE));

        rectangleFilledBtn.setFont(new Font("Liberation Sans", Font.PLAIN, 32));
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
        colorPreview.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        colorPreview.setRollover(true);

        blackBtn.setBackground(Color.black);
        blackBtn.setCursor(handCursor);
        blackBtn.addActionListener(new ColorBtnListener(this));

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

        fileSave.setText("Сохранить");
        fileSave.setCursor(handCursor);
        fileSave.addActionListener(new FileSaver(this));

        menuFile.add(fileOpen);
        menuFile.add(fileSave);

        menuView.setText("Вид");
        menuView.setCursor(handCursor);

        viewTheme.setText("Выбрать тему");
        viewTheme.setCursor(handCursor);

        viewAbout.setText("О приложении");
        viewAbout.setCursor(handCursor);

        menuView.add(viewTheme);
        menuView.add(viewAbout);

        menuBar.add(menuFile);
        menuBar.add(menuView);
        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(toolsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
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
}
