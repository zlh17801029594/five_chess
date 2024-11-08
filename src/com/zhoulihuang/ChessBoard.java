package com.zhoulihuang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.zhoulihuang.Chess.DIAMETER;

public class ChessBoard extends JPanel {
    public static final int MARGIN = 15;
    public static final int GRID_SPAN = 20;
    public static final int ROWS = 18;
    public static final int COLS = 18;
    Image img;
    Chess[] chessList = new Chess[(ROWS + 1) * (COLS + 1)];
    int chessCount;
    boolean isBlack = true;
    boolean isGamming = true;

    public ChessBoard() {
        this.addMouseListener(new MouseMonitor());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i <= ROWS; i++) {
            g.drawLine(MARGIN, MARGIN + i*GRID_SPAN, MARGIN + COLS*GRID_SPAN, MARGIN + i*GRID_SPAN);
        }
        for (int i = 0; i <= COLS; i++) {
            g.drawLine(MARGIN + i*GRID_SPAN, MARGIN, MARGIN + i*GRID_SPAN, MARGIN + ROWS*GRID_SPAN);
        }

        /*Chess c1 = new Chess(this, 2, 3, Color.BLACK);
        c1.draw(g);
        Chess c2 = new Chess(this, 5, 2, Color.WHITE);
        c2.draw(g);*/
        for (int i = 0; i < chessCount; i++) {
            chessList[i].draw(g);
            if (i == chessCount - 1) {
                int xPos = chessList[i].getCol() * GRID_SPAN + MARGIN;
                int yPos = chessList[i].getRow() * GRID_SPAN + MARGIN;
                g.setColor(Color.red);
                g.drawRect(xPos - DIAMETER / 2, yPos - DIAMETER / 2, DIAMETER, DIAMETER);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MARGIN*2 + GRID_SPAN*COLS, MARGIN*2 + GRID_SPAN*ROWS);
    }

    public boolean hasChess(int col, int row) {
        for (int i = 0; i < chessCount; i++) {
            if (chessList[i].getCol() == col && chessList[i].getRow() == row)
                return true;
        }
        return false;
    }

    public boolean hasChess(int col, int row, Color color) {
        for (int i = 0; i < chessCount; i++) {
            if (chessList[i].getCol() == col && chessList[i].getRow() == row && chessList[i].getColor() == color)
                return true;
        }
        return false;
    }

    public boolean isWin(int col, int row) {
        int num = 1;
        for (int i = col - 1; i >= 0; i--) {
            if (!hasChess(i, row, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++num == 5)
                return true;
        }
        for (int i = col + 1; i < COLS; i++) {
            if (!hasChess(i, row, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++num == 5)
                return true;
        }

        num = 1;
        for (int i = row - 1; i >= 0; i--) {
            if (!hasChess(col, i, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++num == 5)
                return true;
        }
        for (int i = row + 1; i < ROWS; i++) {
            if (!hasChess(col, i, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++num == 5)
                return true;
        }

        num = 1;
        for (int i = col - 1, j = row - 1; i >= 0 && j >= 0; i--, j--) {
            if (!hasChess(i, j, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++num == 5)
                return true;
        }
        for (int i = col + 1, j = row + 1; i < COLS && j < ROWS; i++, j++) {
            if (!hasChess(i, j, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++num == 5)
                return true;
        }

        num = 1;
        for (int i = col - 1, j = row + 1; i >= 0 && j < ROWS; i--, j++) {
            if (!hasChess(i, j, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++num == 5)
                return true;
        }
        for (int i = col + 1, j = row - 1; i < COLS && j >= 0; i++, j--) {
            if (!hasChess(i, j, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++num == 5)
                return true;
        }
        return false;
    }

    class MouseMonitor extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (!isGamming) {
                return;
            }
            int col = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
            int row = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
            if (hasChess(col, row)) {
                return;
            }
            if (isWin(col, row)) {
                isGamming = false;
            }
            Chess c = new Chess(ChessBoard.this, col, row, isBlack ? Color.BLACK : Color.WHITE);
            isBlack = !isBlack;
            chessList[chessCount++] = c;
            repaint();
        }
    }

}
