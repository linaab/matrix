import java.util.Random;

public class MyMain {
    public static int[][] multiplyMatrix(int[][] m1, int[][] m2, int threadsCount) {
        int str = m1.length;
        int stb = m2[0].length;
        int[][] result = new int[str][stb];
        int count = str / threadsCount;
        int additional = str % threadsCount;
        Thread[] threads = new Thread[threadsCount];
        int start = 0;
        for (int i = 0; i < threadsCount; i++) {
            int tmp;
            if (i == 0)
                tmp=count + additional;
            else tmp=count;
            threads[i] = new MultiMatrixThread(m1, m2, result, start, start + tmp - 1);
            start += tmp;
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        return result;
    }

    public static int[][] randomMatrix(int n, int m) {
        Random random = new Random();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = 1 + random.nextInt(5);
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] m1 = randomMatrix(5, 5);
        int[][] m2 = randomMatrix(5, 5);
        int[][] res=multiplyMatrix(m1, m2, 5);
        printMatrix(m1);
        System.out.println();
        printMatrix(m2);
        System.out.println();
        printMatrix(res);
        System.out.println();
    }
}


