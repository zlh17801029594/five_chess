package com.zhoulihuang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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
        this.addMouseMotionListener(new MouseMotionMonitor());
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
        int continueCount = 1;
        for (int i = col - 1; i >= 0; i--) {
            if (!hasChess(i, row, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++continueCount == 5)
                return true;
        }
        for (int i = col + 1; i <= COLS; i++) {
            if (!hasChess(i, row, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++continueCount == 5)
                return true;
        }

        continueCount = 1;
        for (int i = row - 1; i >= 0; i--) {
            if (!hasChess(col, i, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++continueCount == 5)
                return true;
        }
        for (int i = row + 1; i <= ROWS; i++) {
            if (!hasChess(col, i, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++continueCount == 5)
                return true;
        }

        continueCount = 1;
        for (int i = col - 1, j = row - 1; i >= 0 && j >= 0; i--, j--) {
            if (!hasChess(i, j, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++continueCount == 5)
                return true;
        }
        for (int i = col + 1, j = row + 1; i <= COLS && j <= ROWS; i++, j++) {
            if (!hasChess(i, j, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++continueCount == 5)
                return true;
        }

        continueCount = 1;
        for (int i = col - 1, j = row + 1; i >= 0 && j <= ROWS; i--, j++) {
            if (!hasChess(i, j, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++continueCount == 5)
                return true;
        }
        for (int i = col + 1, j = row - 1; i <= COLS && j >= 0; i++, j--) {
            if (!hasChess(i, j, isBlack ? Color.BLACK : Color.WHITE))
                break;
            if (++continueCount == 5)
                return true;
        }
        return false;
    }

    public void restartGame() {
        isGamming = true;
        for (int i = 0; i < chessCount; i++) {
            chessList[i] = null;
        }
        chessCount = 0;
        isBlack = true;
        repaint();
    }

    public void goback() {
        if (chessCount == 0) {
            return;
        }
        chessList[chessCount - 1] = null;
        chessCount--;
        isBlack = !isBlack;
        repaint();
    }

    class MouseMonitor extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (!isGamming) {
                return;
            }
            int col = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
            int row = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
            if (col < 0 || col > COLS || row < 0 || row > ROWS) {
                return;
            }
            if (hasChess(col, row)) {
                return;
            }
            Chess c = new Chess(ChessBoard.this, col, row, isBlack ? Color.BLACK : Color.WHITE);
            chessList[chessCount++] = c;
            repaint();
            if (isWin(col, row)) {
                isGamming = false;
                JOptionPane.showMessageDialog(ChessBoard.this, String.format("恭喜，%s赢了！", isBlack ? "黑棋" : "白棋"));
            }
            isBlack = !isBlack;
        }
    }

    class MouseMotionMonitor extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            int xPos = (e.getX() + GRID_SPAN / 2 - MARGIN) / GRID_SPAN;
            int yPos = (e.getY() + GRID_SPAN / 2 - MARGIN) / GRID_SPAN;
            if (isGamming && xPos >= 0 && xPos <= COLS && yPos >= 0 && yPos <= ROWS && !hasChess(xPos, yPos)) {
                ChessBoard.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else {
                ChessBoard.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
}
