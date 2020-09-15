package recursion.queen8;

import org.junit.Test;

/**
 * @author shkstart
 * @create 2020-09-09 12:24
 */
public class Queen8 {
    private static final int MAX_SIZE = 8;
    int[] arr = new int[MAX_SIZE];
    static int countPrint =0;

    @Test
    public void test() {
        check(0);
        System.out.println(countPrint);
    }

    public void check(int n) {
        if (n == MAX_SIZE) {
            print();
            return;
        }

        for (int i = 0; i <MAX_SIZE; i++) {
            arr[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    public void print() {
        countPrint++;
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d  ", arr[i]);
        }
        System.out.println();
    }

    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}
