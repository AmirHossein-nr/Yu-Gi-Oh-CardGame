package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    private static ObjectInputStream objectInputStream;
    public static ObjectOutputStream objectOutputStream;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        objectInputStream = new ObjectInputStream(socket.getInputStream());
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        while (true) {
                            String input = objectInputStream.readUTF();
                            String result = process(input);
                            if (result.equals("")) continue;
                            else if (result.equals("end")) break;
                            objectOutputStream.writeUTF(result);
                            objectOutputStream.flush();
                        }
                        objectOutputStream.close();
                        socket.close();
                        serverSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String process(String input) throws Exception {
        String[] split;
        if (input.startsWith("register")) {
            split = input.split(" ");
            return String.valueOf(ServerController.register(split[1], split[2], split[3]));
        } else if (input.startsWith("login")) {
            split = input.split(" ");
            ServerController.login(split[1], split[2]);
            return "";
        }
        return "";
    }
}
