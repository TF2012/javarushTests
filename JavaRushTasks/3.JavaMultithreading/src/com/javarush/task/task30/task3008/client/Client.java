package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("error waiting");
                return;
            }
        }
        if (clientConnected == true) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
        while (clientConnected == true) {
            String s = ConsoleHelper.readString();
            if (s.equals("exit")) {
                break;
            }
            if (shouldSendTextFromConsole() == true) {
                sendTextMessage(s);
            }
        }
    }
    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Enter server address");
        return ConsoleHelper.readString();
    }
    protected int getServerPort() {
        ConsoleHelper.writeMessage("Enter server port");
        return ConsoleHelper.readInt();
    }
    protected String getUserName() {
        ConsoleHelper.writeMessage("Enter user name");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSendTextFromConsole() {
        return true;
    }
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }
    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.toString());
            clientConnected = false;
        }
    }

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + "joined too chat");
        }
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + "deleted by chat");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message m = connection.receive();
                if ((m.getType()) == (MessageType.NAME_REQUEST)) {
                    getUserName();
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else if ((m.getType()) == (MessageType.NAME_ACCEPTED)) {
                    notifyConnectionStatusChanged(true);
                    break;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message m = connection.receive();
                if ((m.getType()) == (MessageType.TEXT)) processIncomingMessage(m.getData());
                else if ((m.getType()) == (MessageType.USER_ADDED)) informAboutAddingNewUser(m.getData());
                else if ((m.getType()) == (MessageType.USER_REMOVED)) informAboutDeletingNewUser(m.getData());
                else throw new IOException("Unexpected MessageType");
            }
        }
        @Override
        public void run() {
            try {
                Socket socket = new Socket(getServerAddress(), getServerPort());
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }

        }
    }
}
