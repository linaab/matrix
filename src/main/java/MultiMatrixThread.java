public class MultiMatrixThread extends Thread {
    private int startRow, endRow;
    private int[][] m1, m2, result;
    private int n;

    public MultiMatrixThread(int[][] m1, int[][] m2, int[][] result, int startRow, int endRow) {
        this.m1 = m1;
        this.m2 = m2;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
        this.n = m2.length;
    }

    @Override
    public void run() {
        for (int row = startRow; row <= endRow ; row++) {
            for (int col = 0; col < result[row].length; col++) {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += m1[row][i] * m2[i][col];
                }
                result[row][col] = sum;
            }
        }
    }

}

