package ru.shukshin.reduction;

import java.util.*;
import java.util.function.BinaryOperator;

interface ReductionOperation<T>{
    T apply(List<T> list);
    T getIdentity();
}
public class Reduction<T> {
    private T identity;
    private BinaryOperator<T> operation;

    public Reduction(T identity,BinaryOperator<T> operation){
        this.identity=identity;
        this.operation=operation;
    }

    public T reduce(List<T> list){
        if (list == null || list.isEmpty()) {
            return identity;
        }
        T result = identity;
        for (T element : list) {
            result = operation.apply(result, element);
        }
        return result;
    }
    public ReductionOperation<T> getOperation() {
        // Вместо приведения типа лучше создать адаптер
        return new ReductionOperation<T>() {
            @Override
            public T apply(List<T> list) {
                return reduce(list);
            }

            @Override
            public T getIdentity() {
                return identity;
            }
        };
    }

    class StringConcatenation extends Reduction<String> {
        public StringConcatenation() {
            super("",(starter,str)->starter+str);
   }
    class IntegerSum extends Reduction<Integer> {
        public IntegerSum() {
            super(0, Integer::sum);
        }
    }
    class ListSizeSum extends Reduction<List<Integer>>{
        public ListSizeSum(){
            super(0, Integer::sum);
        }
        public int totalElementCount(List<List<Integer>> lists) {
            List<Integer> sizes = new ArrayList<>();
            for (List<Integer> list : lists) {
                sizes.add(list != null ? list.size() : 0);
            }
            return this.reduce(sizes);
        }
    }

}


