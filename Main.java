package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        String textOutput = calc();
        System.out.println(textOutput);
    }

    public static String calc() { //считывает входящую строку, разделяет посимвольно и передаёт значения в метод calculation
        Number number = new Number();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String textInput = scanner.nextLine();
        String error = "";

        ArrayList<String> symbols = new ArrayList<>(Arrays.asList(textInput.split(" ")));

        if (symbols.size() == 3) {
            try{
                if(symbols.get(1).equals("+") || symbols.get(1).equals("-") || symbols.get(1).equals("*") || symbols.get(1).equals("/")) {
                    if (number.romanNumbers.contains(symbols.get(0)) && number.romanNumbers.contains(symbols.get(2))){
                        if(number.convertNumbersToInt(symbols.get(0)) > 0 && number.convertNumbersToInt(symbols.get(0)) < 11 && number.convertNumbersToInt(symbols.get(2)) > 0 && number.convertNumbersToInt(symbols.get(2)) < 11){
                            if (calculation(number.convertNumbersToInt(symbols.get(0)), number.convertNumbersToInt(symbols.get(2)), symbols.get(1)) > 0){
                                return "Результат выражения: " + number.convertNumbersToStr(calculation(number.convertNumbersToInt(symbols.get(0)), number.convertNumbersToInt(symbols.get(2)), symbols.get(1)));
                            } else throw new Exception(error = "Ошибка! Результат выражения не должен быть меньше или равен нулю!");
                        } else throw new Exception(error = "Ошибка! Введённые числа должны быть от 1 до 10 включительно!");
                    } else {
                        if (Integer.parseInt(symbols.get(0)) < 11 && Integer.parseInt(symbols.get(0)) > 0 && Integer.parseInt(symbols.get(2)) < 11 && Integer.parseInt(symbols.get(2)) > 0) {
                            return "Результат выражения: " + calculation(Integer.parseInt(symbols.get(0)), Integer.parseInt(symbols.get(2)), symbols.get(1));
                        } else throw new Exception(error = "Ошибка! Введённые числа должны быть от 1 до 10 включительно!");
                    }
                } else throw new Exception(error = "Неверный оператор!");
            } catch (NumberFormatException nfe) {
                return "Ошибка! Числа должны быть только римскими или только арабскими!";
            } catch (Exception ex) {
                return error;
            }
        } else {
            return "Ошибка! Возможно, вы ввели выражение без пробелов.";
        }
    }

    public static int calculation(int num1, int num2, String operation) { //метод подсчёта входящих чисел после преобразования в int
        int result = 0;
        if (operation.equals("+")) {
            result = num1 + num2;
        }
        if (operation.equals("-")) {
            result = num1 - num2;
        }
        if (operation.equals("*")) {
            result = num1 * num2;
        }
        if (operation.equals("/")) {
            result = num1 / num2;
        }
        return result;
    }
}

class Number{
    ArrayList<String> romanNumbers = new ArrayList<>();
    ArrayList<Integer> arabicNumbers = new ArrayList<>();

    String[] romanNumberArray = {"I","II","III","IV","V","VI","VII","VIII","IX","X","XL","L","XC","C"};
    int[] arabNumberArray = {1,2,3,4,5,6,7,8,9,10,40,50,90,100};

    public Number(){
        romanNumbers.add("I"); romanNumbers.add("II"); romanNumbers.add("III"); romanNumbers.add("IV"); romanNumbers.add("V");
        romanNumbers.add("VI"); romanNumbers.add("VII"); romanNumbers.add("VIII"); romanNumbers.add("IX"); romanNumbers.add("X");
        romanNumbers.add("XL"); romanNumbers.add("L"); romanNumbers.add("XC"); romanNumbers.add("C");

        arabicNumbers.add(1); arabicNumbers.add(2); arabicNumbers.add(3); arabicNumbers.add(4); arabicNumbers.add(5);
        arabicNumbers.add(6); arabicNumbers.add(7); arabicNumbers.add(8); arabicNumbers.add(9); arabicNumbers.add(10);
        arabicNumbers.add(40); arabicNumbers.add(50); arabicNumbers.add(90); arabicNumbers.add(100);
    }

    public int convertNumbersToInt(String number){
        int counter = 0;
        int result = 0;
        for(String s : romanNumbers) {
            if(s.equals(number)){
                result = arabicNumbers.get(counter);
            }
            counter++;
        }
        return result;
    }

    public String convertNumbersToStr(int number){
        StringBuilder result = new StringBuilder();
        int arraySize = arabNumberArray.length - 1;
        while (number >0){
            if(arabNumberArray[arraySize] < number || arabNumberArray[arraySize] == number){
                number -= arabNumberArray[arraySize];
                result.append(romanNumberArray[arraySize]);
            }else{
                arraySize--;
            }
        }
        return result.toString();
    }
}
