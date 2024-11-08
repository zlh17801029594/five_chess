package com.zhoulihuang;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static com.zhoulihuang.ChessBoard.GRID_SPAN;
import static com.zhoulihuang.ChessBoard.MARGIN;

public class Chess {
    public static final int DIAMETER = GRID_SPAN - 2;
    ChessBoard cb;
    private int col;
    private int row;
    private Color color;

    public Chess(ChessBoard cb, int col, int row, Color color) {
        this.cb = cb;
        this.col = col;
        this.row = row;
        this.color = color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Color getColor() {
        return color;
    }

    public void draw(Graphics g) {
        int xPos = col * GRID_SPAN + MARGIN;
        int yPos = row * GRID_SPAN + MARGIN;

        Graphics2D g2d = (Graphics2D) g;
        RadialGradientPaint paint = null;
        int x = xPos + DIAMETER / 4;
        int y = yPos - DIAMETER / 4;
        float[] f = {0f, 1f};
        Color[] c = {Color.WHITE, Color.BLACK};
        if (color == Color.black) {
            paint = new RadialGradientPaint(x, y, DIAMETER, f, c);
        } else if (color == Color.white) {
            paint = new RadialGradientPaint(x, y, DIAMETER * 2, f, c);
        }
        g2d.setPaint(paint);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

        Ellipse2D e = new Ellipse2D.Float(xPos - DIAMETER / 2, yPos - DIAMETER / 2, DIAMETER, DIAMETER);
        g2d.fill(e);
    }
}
