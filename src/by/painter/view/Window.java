package by.painter.view;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import by.painter.controller.buttoncontrol.ClearBtnListener;
import by.painter.controller.buttoncontrol.InstrumentBtnListener;
import by.painter.model.Instruments;
import by.painter.model.Painter;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private Painter painter;
	private JButton penBtn;
	private JButton rectBtn;
	private JButton clearBtn;
	private JMenuBar menuBar;
	private PaintCanva mainCanva;
	private GroupLayout layout;
	private GroupLayout canvaLayout;

	public Window(Painter p) {
		this.painter = p;
		initElements("Painter V 0.2");
	}

	private void initElements(String name) {
		super.setTitle(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(255, 255, 255));

		layout = new GroupLayout(getContentPane());
		mainCanva = new PaintCanva(1440, 900);
		mainCanva.setBackground(new Color(255, 255, 255));
		canvaLayout = new GroupLayout(mainCanva);
		menuBar = new JMenuBar();
		penBtn = new JButton();
		rectBtn = new JButton();
		clearBtn = new JButton();

		penBtn.setText("Карандаш");
		penBtn.setFocusPainted(false);
		penBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		penBtn.addActionListener(new InstrumentBtnListener(painter, Instruments.PEN));

		rectBtn.setText("Прямоугольник");
		rectBtn.setFocusPainted(false);
		rectBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rectBtn.addActionListener(new InstrumentBtnListener(painter, Instruments.RECTANGLE));

		clearBtn.setText("Очистить");
		clearBtn.setFocusPainted(false);
		clearBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clearBtn.addActionListener(new ClearBtnListener(this));

		menuBar.add(penBtn);
		menuBar.add(rectBtn);
		menuBar.add(clearBtn);
		setJMenuBar(menuBar);

		mainCanva.setLayout(canvaLayout);
		canvaLayout.setHorizontalGroup(
				canvaLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1440, Short.MAX_VALUE));
		canvaLayout.setVerticalGroup(
				canvaLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 900, Short.MAX_VALUE));

		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addGap(20, 20, 20).addComponent(mainCanva, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addGap(23, 23, 23).addComponent(mainCanva, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(32, Short.MAX_VALUE)));

		pack();
		super.setVisible(true);
	}

	public PaintCanva getMainCanva() {
		return mainCanva;
	}

	public void setMainCanva(PaintCanva mainCanva) {
		this.mainCanva = mainCanva;
	}

}
