package com.zhoulihuang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        ActionMonitor actionMonitor = new ActionMonitor();
        startButton.addActionListener(actionMonitor);
        backButton.addActionListener(actionMonitor);
        exitButton.addActionListener(actionMonitor);
        aggregateToolBar(toolBar, startButton, backButton, exitButton);
        boardPanel = new ChessBoard();
        aggregateFive(this, toolBar, boardPanel);

        this.setLocation(200, 200);
        this.pack();
        this.setResizable(false);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void aggregateFive(JFrame frame, JToolBar toolBar, ChessBoard boardPanel) {
        // 组合工具栏、棋盘（在窗口中的）布局
        frame.add(toolBar, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);
    }

    private void aggregateToolBar(JToolBar toolBar, JButton startButton, JButton backButton, JButton exitButton) {
        // 组合按钮（在工具栏中的）布局
        toolBar.add(startButton);
        toolBar.add(backButton);
        toolBar.add(exitButton);
    }

    class ActionMonitor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                boardPanel.restartGame();
            } else if (e.getSource() == backButton) {
                boardPanel.goback();
            } else if (e.getSource() == exitButton) {
                System.exit(0);
            }
        }
    }

}
