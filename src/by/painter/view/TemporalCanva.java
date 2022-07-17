package by.painter.view;

import java.awt.Graphics;

import javax.swing.JLabel;

import by.painter.controller.DrawingInstrument;

public class TemporalCanva extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawingInstrument instr;

	public TemporalCanva(DrawingInstrument instr) {
		this.instr = instr;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		instr.drawFigure(g);
	}

}
