package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<String, Connection>();
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())){
            ConsoleHelper.writeMessage("Server on");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendBroadcastMessage(Message message) {
        for (Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Message doesn't send");
            }
        }
    }
    private static class Handler extends Thread {
        Socket socket;
        Handler (Socket socket) {
            this.socket = socket;
        }
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Boolean b = true;
            String name = null;
            while (b) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message m = connection.receive();
                 if ((m.getType() == MessageType.USER_NAME)
                        && !m.getData().isEmpty()
                        && (m.getType() != null)
                        && (m.getData() !="")) {
                    if (!connectionMap.containsKey(m.getData())) {
                        connectionMap.put(m.getData(), connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        ConsoleHelper.writeMessage(m.getData() + "accepted");
                        b = false;
                        name = m.getData();
                    }
                }
            }
            return name;
        }
        private void notifyUsers(Connection connection, String userName) throws IOException {
                for (String s : connectionMap.keySet()) {
                    if (!s.equalsIgnoreCase(userName)) {
                        connection.send(new Message(MessageType.USER_ADDED, s));
                    }
                }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message m = connection.receive();
                if (m.getType() == MessageType.TEXT) {
                    Server.sendBroadcastMessage(new Message(MessageType.TEXT, String.format("%s: %s", userName, m.getData())));
                } else {
                    ConsoleHelper.writeMessage("error");
                }
            }
        }

        public void run() {
            ConsoleHelper.writeMessage(socket.getRemoteSocketAddress().toString());
            String userName = null;
            try (Connection connection = new Connection(socket);) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("error by server");
            }
            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Connection is end");
        }
    }
}