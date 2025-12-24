package ru.shukshin.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import ru.shukshin.comparable.MyNumber;
import ru.shukshin.fillList.ListFull;
import ru.shukshin.filter.*;
import ru.shukshin.function.*;

import ru.shukshin.reduction.Reduction;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1: 1.3 Сравнимое ");
            System.out.println("2: 1.6 Обобщенный изменяемый массив");
            System.out.println("3: 2.4 Заполнение списка");
            System.out.println("4: 3.1 Функция");
            System.out.println("5: 3.2 Фильтр");
            System.out.println("6: 3.3 Сокращение");
            System.out.println("7: 3.4 Коллекционирование");
            System.out.println("0: Выход");
            choice=scanner.nextInt();
            List<String> strings=Arrays.asList("qwerty", "asdfg", "zx");
            List<Integer> numbers=Arrays.asList(1,-3,7);
            List<int[]> arrays=Arrays.asList(
                    new int[]{4,6,5,7,9},
                    new int[]{0,0,0,48,89},
                    new int[]{-1,-2,-2,-4}
            );
            switch (choice){
                case 1:
                    MyNumber num1 =new MyNumber(12.554);
                    MyNumber num2=new MyNumber(25);
                    MyNumber num3=new MyNumber(25);
                    int res1=num1.compare(num2);
                    int res2=num2.compare(num1);
                    int res3=num2.compare(num3);
                    System.out.println(num1+" сравнить с "+num2+"="+res1);
                    System.out.println(num2+" сравнить с "+num1+"="+res2);
                    System.out.println(num2+" сравнить с "+num3+"="+res3);
                    break;
                case 2:
                    break;
                case 3:
                    List<Integer> intList=new ArrayList<>();
                    ListFull.fillList(intList);
                    System.out.println("Список: "+ ListFull.toString(intList));
                    break;
                case 4:
                    Function function1 = new Function("Преобразование строк в их длины");
                    System.out.println(function1.toString());
                    System.out.println("Начальный список: " + strings);
                    List<Integer> stringLengths = Function.map(strings, new ApplyFunction<String, Integer>() {
                        @Override
                        public Integer apply(String value) {
                            return value.length();
                        }
                    });
                    System.out.println("Длины строк: " + stringLengths);

                    Function function2 = new Function("Модуль отрицательных чисел");
                    System.out.println(function2.toString());
                    System.out.println("Начальный список: " + numbers);
                    List<Integer> absNumbers = Function.map(numbers, new ApplyFunction<Integer, Integer>() {
                        @Override
                        public Integer apply(Integer value) {
                            return Math.abs(value);
                        }
                    });
                    System.out.println("Числа после преобразования: " + absNumbers);

                    Function function3 = new Function("Максимальные значения в массивах");
                    System.out.println(function3.toString());
                    System.out.println("Исходные массивы: " + Function.arraysToString(arrays));

                    List<Integer> maxValues = Function.map(arrays, new ApplyFunction<int[], Integer>() {
                        @Override
                        public Integer apply(int[] array) {
                            if (array == null || array.length == 0) {
                                return null; // или можно вернуть какое-то значение по умолчанию
                            }
                            int max = array[0];
                            for (int i = 1; i < array.length; i++) {
                                if (array[i] > max) {
                                    max = array[i];
                                }
                            }
                            return max;
                        }
                    });
                    System.out.println("Максимальные значения: " + maxValues);
                    break;
                case 5:
                    Filter filter1=new Filter("Удаляет строки менее трех символов");
                    System.out.println(filter1.toString());
                    List<String> bigWords= Filter.filter(strings,new TestFilter<String>(){
                        public boolean test(String value){
                            return value.length()>=3;
                        }
                    });
                    System.out.println("Начальный список: "+strings);
                    System.out.println(bigWords);

                    Filter filter2=new Filter("Удаляет все положительные числа");
                    System.out.println(filter2.toString());
                    List<Integer> filtNumbers = Filter.filter(numbers, new TestFilter<Integer>() {
                        public boolean test(Integer value) {
                            return value < 0;
                        }
                    });
                    System.out.println("Начальный список: "+numbers);
                    System.out.println(filtNumbers);

                    Filter filter3=new Filter("Убирает массивы, где есть положительные числа");
                    System.out.println(filter3.toString());
                    System.out.println("Исходные массивы: "+filter3.arraysToString(arrays));
                    List<int[]> filtArrays = Filter.filter(arrays, new TestFilter<int[]>() {
                        public boolean test(int[] array) {
                            for (int num : array) {
                                if (num > 0) {
                                    return false; // Найден положительный элемент - не подходит
                                }
                            }
                            return true; // Все элементы не положительные
                        }
                    });
                    System.out.println(filter3.arraysToString(filtArrays));

                    break;
                case 6:

                    Reduction reduce1=new Reduction("Формирует одну большую строку");
                    System.out.println(reduce1.toString());
                    System.out.println("Начальный список: "+strings);
                    String joined = Reduction.reduce(strings, "", (a, b) -> a + b);
                    System.out.println("Объединённая строка: " + joined);

                    Reduction reduce2=new Reduction("Сумма значений массива");
                    System.out.println(reduce2.toString());
                    System.out.println("Начальный список: "+numbers);
                    Integer sum = Reduction.reduce(numbers, 0, Integer::sum);
                    System.out.println("Сумма чисел: " + sum);

                    Reduction reduce3=new Reduction("Сумма значений массива");
                    System.out.println(reduce3.toString());
                    System.out.println("Исходные массивы: "+reduce3.arraysToString(arrays));
                    List<Integer> sizes = new ArrayList<>();
                    for (int[] array : arrays) {
                        sizes.add(array != null ? array.length : 0);
                    }
                    Integer totalCount2 = Reduction.reduce(sizes, 0, Integer::sum);
                    System.out.println("Общее количество элементов (вариант 2): " + totalCount2);

                    break;
                case 7:

                    break;
            }
        }while(choice!=0);
    }
}