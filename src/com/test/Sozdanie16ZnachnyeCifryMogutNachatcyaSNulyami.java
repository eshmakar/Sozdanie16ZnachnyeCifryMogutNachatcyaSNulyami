package com.test;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Sozdanie16ZnachnyeCifryMogutNachatcyaSNulyami {

    public static void main(String[] args) throws IOException {
        System.out.println("Идет создание 1 000 000 значении...");
        String arr[] = new String[1_000_000];
        boolean isTrue = false;
        int mln = 0;

        DecimalFormat d = new DecimalFormat(",###0"); // шаблон, как будет отображатся, здесь после 4 знака разделяются пробелами
        d.setMinimumIntegerDigits(16); //минимальное кол-во цифр
        d.setMaximumIntegerDigits(16); //максимальное кол-во цифр

        for (int i = 0; i < arr.length; i++) {
            String s = String.valueOf(new Random().nextLong()).replace("-", ""); // генерируются рандомные цифры, если они имеют знак минус, то минус убирается
            String s2 = d.format(Long.parseLong(s)); // сохраняется как текстовое значение
            arr[i] = s2;
            if (i % 100_000 == 0) System.out.println((mln += 100) + " тыс."); // выводит после каждого 100 тысыч кол-во выполененых цифр
            try (FileWriter f = new FileWriter("C:/1/1mln.txt", true)) {
                f.write(arr[i]);
                f.write("\n");
            }
        }

        System.out.println("Создано 1 млн значении. Угадайте хотя бы одну");

        while (true) { // Пользователь может попробовать бесконечно раз угадать эти варианты, хотя бы одну)
            String inputFromUser = new Scanner(System.in).nextLine();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(inputFromUser)) {
                    System.out.println("YES");
                    isTrue = true;
                }
            }
            if (!isTrue) System.out.println("Ты не нашел"); // если юзер ничего не нашел
        }
    }
}