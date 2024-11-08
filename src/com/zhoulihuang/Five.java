package com.zhoulihuang;

import javax.swing.*;
import java.awt.*;

public class Five extends JFrame {
    private JToolBar toolBar;
    private JButton startButton, backButton, exitButton;
    private ChessBoard boardPanel;

    public Five() {
        super("单机版五子棋");
        toolBar = new JToolBar();
        startButton = new JButton("重新开始");
        backButton = new JButton("悔棋");
        exitButton = new JButton("退出");
        aggregateToolBar(toolBar, startButton, backButton, exitButton);
        boardPanel = new ChessBoard();
        aggregateFive(toolBar, boardPanel);

        this.setLocation(200, 200);
        this.pack();
        this.setResizable(false);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void aggregateFive(JToolBar toolBar, ChessBoard boardPanel) {
        // 组合工具栏、棋盘（在窗口中的）布局
        this.add(toolBar, BorderLayout.NORTH);
        this.add(boardPanel, BorderLayout.CENTER);
    }

    private void aggregateToolBar(JToolBar toolBar, JButton startButton, JButton backButton, JButton exitButton) {
        // 组合按钮（在工具栏中的）布局
        toolBar.add(startButton);
        toolBar.add(backButton);
        toolBar.add(exitButton);
    }
}