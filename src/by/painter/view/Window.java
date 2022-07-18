package by.painter.view;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import by.painter.controller.buttoncontrol.ClearBtnListener;
import by.painter.controller.buttoncontrol.InstrumentBtnListener;
import by.painter.model.Instrument;
import by.painter.model.Painter;

public class Window extends JFrame implements Viewable {

    private static final long serialVersionUID = 1L;
    private Painter painter;
    private PaintCanvas mainCanvas;

    public Window(Painter painter) {
        this.painter = painter;
        initElements("Painter V 0.3");
    }

    @Override
    public void initElements(String name) {
        super.setTitle(name);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));

        GroupLayout layout = new GroupLayout(getContentPane());
        mainCanvas = new PaintCanvas(800, 600);
        mainCanvas.setBackground(new Color(255, 255, 255));
        GroupLayout canvaLayout = new GroupLayout(mainCanvas);
        JMenuBar menuBar = new JMenuBar();
        JButton penBtn = new JButton();
        JButton rectBtn = new JButton();
        JButton clearBtn = new JButton();

        penBtn.setText("Карандаш");
        penBtn.setFocusPainted(false);
        penBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        penBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.PEN));

        rectBtn.setText("Прямоугольник");
        rectBtn.setFocusPainted(false);
        rectBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rectBtn.addActionListener(new InstrumentBtnListener(painter, Instrument.RECTANGLE));

        clearBtn.setText("Очистить");
        clearBtn.setFocusPainted(false);
        clearBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearBtn.addActionListener(new ClearBtnListener(this));

        menuBar.add(penBtn);
        menuBar.add(rectBtn);
        menuBar.add(clearBtn);
        setJMenuBar(menuBar);

        mainCanvas.setLayout(canvaLayout);
        canvaLayout.setHorizontalGroup(
                canvaLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 800, Short.MAX_VALUE));
        canvaLayout.setVerticalGroup(
                canvaLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 600, Short.MAX_VALUE));

        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout
                        .createSequentialGroup().addGap(20, 20, 20).addComponent(mainCanvas, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout
                        .createSequentialGroup().addGap(23, 23, 23).addComponent(mainCanvas, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(32, Short.MAX_VALUE)));

        pack();
    }

    @Override
    public PaintCanvas getMainCanvas() {
        return mainCanvas;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public void setVisible(Boolean bool) {
        super.setVisible(bool);
    }
}
