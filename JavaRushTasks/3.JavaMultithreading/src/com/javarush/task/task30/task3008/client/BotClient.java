package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class BotClient extends Client {
    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }
    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }
    @Override
    protected String getUserName() {
        int x = (int) (Math.random() * 100);
        return ("date_bot_" + x);
    }
    public class BotSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] sB = message.split(": ");
            if (sB.length != 2) return;
            String s = null;
            switch (sB[1]) {
                    case "дата":
                        s = "d.MM.YYYY";
                        break;
                    case "день":
                        s = "d";
                        break;
                    case "месяц":
                        s = "MMMM";
                        break;
                    case "год":
                        s = "YYYY";
                        break;
                    case "время":
                        s = "H:mm:ss";
                        break;
                    case "час":
                        s = "H";
                        break;
                    case "минуты":
                        s = "m";
                        break;
                    case "секунды":
                        s = "s";
                        break;
            }
            if (s != null){
                String answer = new SimpleDateFormat(s).format(Calendar.getInstance().getTime());
                BotClient.this.sendTextMessage("Информация для " + sB[0] + ": " + answer);
            }
        }
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            BotClient.this.sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }
}
