package ru.vsu.sc.uliyanov_n_s;

public class ArrayTransform {
    public void transformArray(int[][] arr, int numOfRaw) {
        for (int i = 0; i < arr.length; i++) {
            for (int l = 0; l < arr[0].length - 1; l++) {
                if (arr[numOfRaw - 1][l + 1] < arr[numOfRaw - 1][l]) {
                    for (int j = 0; j < arr.length; j++) {
                        int temp = arr[j][l];
                        arr[j][l] = arr[j][l + 1];
                        arr[j][l + 1] = temp;
                    }
                }
            }
        }
    }
}
