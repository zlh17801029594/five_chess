package com.zhoulihuang.net;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FiveServer extends JFrame {
    private JLabel label;
    private TextArea textArea;
    private JButton closeButton;
    private ServerSocket ss;
    public static final int TCP_PORT = 4801;

    public FiveServer() throws HeadlessException {
        super("Java五子棋服务器");

        label = new JLabel("当前连接数:", SwingConstants.LEFT);
        textArea = new TextArea("", 22, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        closeButton = new JButton("关闭服务器");
        this.add(label, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
        this.add(closeButton, BorderLayout.SOUTH);

        this.setLocation(400, 100);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void startServer() {
        try {
            ss = new ServerSocket(TCP_PORT);
            while (true) {
                Socket s = ss.accept();
                String msg = s.getInetAddress().getHostAddress() + ":" + s.getPort() + ":" + s.getLocalPort() + " Player" + "\n";
                textArea.append(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FiveServer fs = new FiveServer();
        fs.startServer();
    }
}
