package com.zhoulihuang;

import java.awt.*;

import static com.zhoulihuang.ChessBoard.GRID_SPAN;

public class Chess {
    ChessBoard cb;
    private int col;
    private int row;
    private Color color;
    public static final int DIAMETER = GRID_SPAN - 2;

    public Chess(ChessBoard cb, int col, int row, Color color) {
        this.cb = cb;
        this.col = col;
        this.row = row;
        this.color = color;
    }

    public void draw(Graphics g) {
        //
    }
}
