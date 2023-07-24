package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Укажите файл из какого архива будем удалять:");
        ZipFileManager zipFileManager = getZipFileManager();
        Path path = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(path);
    }
}
