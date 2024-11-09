package com.zhoulihuang.net;

import com.zhoulihuang.ChessBoard;

import javax.swing.*;
import java.awt.*;

public class FiveClient extends JFrame {
    private ChessBoard boardPanel;
    private PanelTiming timing;
    private PanelUserList userList;
    private PanelMessage message;
    private PanelControl control;

    public FiveClient() throws HeadlessException {
        super("五子棋客户端");

        boardPanel = new ChessBoard();
        this.add(boardPanel, BorderLayout.CENTER);
        timing = new PanelTiming();
        userList = new PanelUserList();
        message = new PanelMessage();
        Panel east = new Panel();
        east.setLayout(new BorderLayout());
        east.add(timing, BorderLayout.NORTH);
        east.add(userList, BorderLayout.CENTER);
        east.add(message, BorderLayout.SOUTH);
        this.add(east, BorderLayout.EAST);
        control = new PanelControl();
        this.add(control, BorderLayout.SOUTH);

        this.setLocation(300, 100);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FiveClient();
    }
}
