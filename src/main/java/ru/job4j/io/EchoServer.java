package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String response = "";
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.startsWith("GET")) {
                            response = str.substring(str.indexOf("=") + 1, str.indexOf(" HTTP/1.1"));
                        }
                    }
                    if (!"Hello".equals(response) && !"Exit".equals(response)) {
                        out.write("What".getBytes());
                    } else {
                        out.write((response + ", dear friend.").getBytes());
                    }
                    if ("Exit".equals(response)) {
                        server.close();
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("No socket access", e);
        }
    }
}
