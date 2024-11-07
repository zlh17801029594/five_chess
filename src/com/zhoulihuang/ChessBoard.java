package com.zhoulihuang;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {
    public static final int MARGIN = 15;
    public static final int GRID_SPAN = 20;
    public static final int ROWS = 18;
    public static final int COLS = 18;
    Image img;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i <= ROWS; i++) {
            g.drawLine(MARGIN, MARGIN + i*GRID_SPAN, MARGIN + COLS*GRID_SPAN, MARGIN + i*GRID_SPAN);
        }
        for (int i = 0; i <= COLS; i++) {
            g.drawLine(MARGIN + i*GRID_SPAN, MARGIN, MARGIN + i*GRID_SPAN, MARGIN + ROWS*GRID_SPAN);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MARGIN*2 + GRID_SPAN*COLS, MARGIN*2 + GRID_SPAN*ROWS);
    }
}
