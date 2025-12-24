package ru.shukshin.reduction;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;


public class Reduction {
    private String name;
    public Reduction(String name){
        this.name=name;
    }
    public static <T> T reduce(List<T> input, T identity, BinaryOperator<T> reducer) {
        if (reducer == null) {
            throw new IllegalArgumentException("Reducer function cannot be null");
        }
        T result = identity;
        if (input == null || input.isEmpty()) {
            return result; // Всегда возвращаем identity для пустого списка
        }
        if (input != null) {
            for (T value : input) {
                if (value != null) {
                    result = reducer.apply(result, value);
                }
            }
        }
        return result;
    }

    public static String arraysToString(List<int[]> arrays) {
        String result = "[";
        for (int i = 0; i < arrays.size(); i++) {
            result += Arrays.toString(arrays.get(i));
            if (i <arrays.size()-1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

    public String toString() {
        return "Предназначение функции сокращения: "+name;
    }
}