package com.javarush.task.pro.task15.task1504;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Перепутанные байты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        try (Scanner scanner = new Scanner(System.in);
             InputStream input = Files.newInputStream(Paths.get(scanner.nextLine()));
             OutputStream output = Files.newOutputStream(Paths.get(scanner.nextLine()))) {
            byte[] allByte = input.readAllBytes();
            byte[] copyAllByte = new byte[allByte.length];
            int length;
            if ((allByte.length % 2 != 0)) {
                copyAllByte[allByte.length - 1] = allByte[allByte.length - 1];
                length = allByte.length - 1;
            } else {
                length = allByte.length;
            }
            for (int i = 0; i < length; i += 2) {
                copyAllByte[i] = allByte[i + 1];
                copyAllByte[i + 1] = allByte[i];
            }
            output.write(copyAllByte);
        }
    }
}