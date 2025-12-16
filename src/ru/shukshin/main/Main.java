package ru.shukshin.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ru.shukshin.comparable.MyNumber;
import ru.shukshin.fillList.ListFull;
import ru.shukshin.filter.*;
import ru.shukshin.function.Function;

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
                    Function function1=new Function("Находит длины строк");
                    System.out.println(function1.toString());
                    System.out.println("Начальный список: "+strings);
                    List<Integer> lengths=Function.map(strings,s->s.length());
                    System.out.println(lengths);

                    Function function2=new Function("Меняет отрицательные значения на положительные");
                    System.out.println(function2.toString());
                    System.out.println("Начальный список: "+numbers);
                    List<Integer> positive=Function.map(numbers,n->n<0 ? -n : n);
                    System.out.println(positive);

                    Function function3=new Function("Находит максимальный элемент  массиве");
                    System.out.println(function3.toString());
                    System.out.println("Начальные массивы:"+function3.arraysToString(arrays));
                    List<Integer> maxValues=Function.map(arrays,arr->{
                        int max=arr[0];
                        for(int i=0;i<arr.length;i++){
                            if(arr[i]>max){
                                max=arr[i];
                            }
                        }
                        return max;
                    });
                    System.out.println(maxValues);
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

                    Filter filter2=new Filter("Удаляет все отрицательные числа");
                    System.out.println(filter2.toString());
                    List<Integer> filtNumbers = Filter.filter(numbers, new TestFilter<Integer>() {
                        public boolean test(Integer value) {
                            return value > 0;
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
                    break;
                case 7:
                    break;
            }
        }while(choice!=0);
    }
}