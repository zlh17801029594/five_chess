package com.zhoulihuang.net;

import javax.swing.*;
import java.awt.*;

public class PanelControl extends Panel {
    public JLabel iPLabel = new JLabel("服务器IP:", SwingConstants.LEFT);
    public TextField inputIP = new TextField("127.0.0.1", 12);
    public JButton connectButton = new JButton("连接主机");
    public JButton joinGameButton = new JButton("加入游戏");
    public JButton cancelGameButton = new JButton("放弃游戏");
    public JButton exitGameButton = new JButton("关闭程序");

    public PanelControl() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(200, 200, 200));
        this.add(iPLabel);
        this.add(inputIP);
        this.add(connectButton);
        this.add(joinGameButton);
        this.add(cancelGameButton);
        this.add(exitGameButton);
    }
}
