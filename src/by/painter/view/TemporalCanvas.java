package by.painter.view;

import java.awt.Graphics;

import javax.swing.JLabel;

import by.painter.controller.DrawingInstrument;

public class TemporalCanvas extends JLabel {

    private static final long serialVersionUID = 1L;
    private final DrawingInstrument instr;

    public TemporalCanvas(DrawingInstrument instr) {
        this.instr = instr;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        instr.drawFigure(g);
    }

}
