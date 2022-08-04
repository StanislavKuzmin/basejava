package com.urase.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {

    public int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (l, r) -> l * 10 + r);
    }

    public List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .filter(integers.stream().mapToInt(Integer::intValue)
                        .sum() % 2 != 0 ? n -> n % 2 == 0 : n -> n % 2 != 0)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        StreamApi streamApi = new StreamApi();
        int[] array1 = {1, 2, 3, 3, 2, 3};
        int[] array2 = {9, 8};
        System.out.println(streamApi.minValue(array1));
        System.out.println(streamApi.minValue(array2));
        System.out.println(streamApi.oddOrEven(Arrays.stream(array1).boxed().collect(Collectors.toList())));
        System.out.println(streamApi.oddOrEven(Arrays.stream(array2).boxed().collect(Collectors.toList())));
    }
}
