package com.zhoulihuang.net;

import com.zhoulihuang.ChessBoard;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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

    private void connect() {
        try {
            Socket s = new Socket("127.0.0.1", FiveServer.TCP_PORT);
            message.messageArea.append("已连接\n");
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            boardPanel.setGoCallback((x, y) -> {
                try {
                    dos.writeUTF(Command.GO + ":" + x + ":" + y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            DataInputStream dis = new DataInputStream(s.getInputStream());
            new Thread(() -> {
                while (true) {
                    try {
                        String msg = dis.readUTF();
                        String[] words = msg.split(":");
                        if (words[0].equals(Command.ADD)) {
                            userList.userList.add(words[1]);
                            message.messageArea.append(words[1] + "\n");
                        } else if (words[0].equals(Command.GO)) {
                            boardPanel.go(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FiveClient fc = new FiveClient();
        fc.connect();
    }
}
