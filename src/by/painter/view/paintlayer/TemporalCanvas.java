package by.painter.view.paintlayer;

import java.awt.Graphics;

import javax.swing.JLabel;

import by.painter.model.instrument.DrawingInstrument;

public class TemporalCanvas extends JLabel {

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
