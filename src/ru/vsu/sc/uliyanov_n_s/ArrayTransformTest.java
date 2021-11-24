package ru.vsu.sc.uliyanov_n_s;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.sc.uliyanov_n_s.utils.ArrayUtils;

public class ArrayTransformTest {
    ArrayTransform arrayTransform = new ArrayTransform();

    @Test
    public void test1() {
        int[][] correctResult = ArrayUtils.readIntArray2FromFile("testFiles/answer1.txt");
        int[][] array = ArrayUtils.readIntArray2FromFile("testFiles/test1.txt");

        assert array != null;
        arrayTransform.transformArray(array, 1);

        Assert.assertArrayEquals(correctResult, array);
    }

    @Test
    public void test2() {
        int[][] correctResult = ArrayUtils.readIntArray2FromFile("testFiles/answer2.txt");
        int[][] array = ArrayUtils.readIntArray2FromFile("testFiles/test2.txt");

        assert array != null;
        arrayTransform.transformArray(array, 2);

        Assert.assertArrayEquals(correctResult, array);
    }

    @Test
    public void test3() {
        int[][] correctResult = ArrayUtils.readIntArray2FromFile("testFiles/answer3.txt");
        int[][] array = ArrayUtils.readIntArray2FromFile("testFiles/test3.txt");

        assert array != null;
        arrayTransform.transformArray(array, 3);

        Assert.assertArrayEquals(correctResult, array);
    }

    @Test
    public void test4() {
        int[][] correctResult = ArrayUtils.readIntArray2FromFile("testFiles/answer4.txt");
        int[][] array = ArrayUtils.readIntArray2FromFile("testFiles/test4.txt");

        assert array != null;
        arrayTransform.transformArray(array, 4);

        Assert.assertArrayEquals(correctResult, array);
    }

    @Test
    public void test5() {
        int[][] correctResult = ArrayUtils.readIntArray2FromFile("testFiles/answer5.txt");
        int[][] array = ArrayUtils.readIntArray2FromFile("testFiles/test5.txt");

        assert array != null;
        arrayTransform.transformArray(array, 5);

        Assert.assertArrayEquals(correctResult, array);
    }
}