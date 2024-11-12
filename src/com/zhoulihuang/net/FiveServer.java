package com.zhoulihuang.net;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FiveServer extends JFrame {
    private JLabel label;
    private TextArea textArea;
    private JButton closeButton;
    private ServerSocket ss;
    public static final int TCP_PORT = 4801;
    private List<Socket> clients = new ArrayList<>();

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

    private void addMeToAllUser(Socket s) {
        for (Socket client : clients) {
            try {
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF(Command.ADD + ":" + s.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addAllUserToMe(Socket s) {
        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            for (Socket client : clients) {
                dos.writeUTF(Command.ADD + ":" + client.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            ss = new ServerSocket(TCP_PORT);
            while (true) {
                Socket s = ss.accept();
                String msg = s.getInetAddress().getHostAddress() + ":" + s.getPort() + ":" + s.getLocalPort() + " Player" + "\n";
                textArea.append(msg);
                addAllUserToMe(s);
                addMeToAllUser(s);
                clients.add(s);
                new Thread(() -> {
                    while (true) {
                        try {
                            DataInputStream dis = new DataInputStream(s.getInputStream());
                            String msgRcv = dis.readUTF();
                            String[] words = msgRcv.split(":");
                            if (words[0].equals(Command.GO)) {
                                for (Socket client : clients) {
                                    if (client == s) {
                                        continue;
                                    }
                                    DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                                    dos.writeUTF(msgRcv);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }).start();
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
