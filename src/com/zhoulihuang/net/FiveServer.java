package com.zhoulihuang.net;

import javax.swing.*;
import java.awt.*;

public class FiveServer extends JFrame {
    private JLabel label;
    private TextArea textArea;
    private JButton closeButton;

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

    public static void main(String[] args) {
        new FiveServer();
    }
}
