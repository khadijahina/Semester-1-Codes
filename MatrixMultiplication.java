package Matrix;

import java.util.Random;

public class MatrixMultiplication {

	private static int ROWS = 3;
	private static int COLUMNS = 3;
	private static Random rand = new Random();
	private static int[][] mat1 = new int[][] {{1, 5, 0}, 
		{6, 8, 5}, 
		{7, 3, 9}};
	private static int[][] mat2 = new int[][] {{4, 9, 3},
		{6, 2, 1},
		{6, 0, 8}};
	private static int[][] result;

	private static int[][] initializeMatrix() {
		int[][] mat = new int[ROWS][COLUMNS];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = rand.nextInt(10);
			}
		}
		return mat;
	}

	private static void printMatrix(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		mat1 = initializeMatrix();
		System.out.println("This is first matrix:");
		printMatrix(mat1);
		mat2 = initializeMatrix();
		System.out.println("\nThis is second matrix:");
		printMatrix(mat2);
		result = new int[ROWS][COLUMNS];
		try {
			Thread[] th = new Thread[ROWS];
			for (int i = 0; i < ROWS; i++) {
				th[i] = new Thread(new MatrixMultiplier(new Multiply(i)));
				th[i].start();
			}
			for (int i = 0; i < ROWS; i++) {
				th[i].join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nThis is result matrix:");
		printMatrix(result);
	}

	public static class Multiply extends MatrixMultiplication {
		private int row;

		public Multiply(int row) {
			this.row = row;
		}

		public void multiplyMatrix() {
			for (int i = 0; i < result.length; i++) {
				for (int j = 0; j < result[i].length; j++) {
					result[i][row] += mat1[i][j] * mat2[j][row];
				}
			}
		}
	}

	public static class MatrixMultiplier implements Runnable {
		private final Multiply mul;

		public MatrixMultiplier(Multiply mul) {
			this.mul = mul;
		}

		public void run() {
			mul.multiplyMatrix();
		}
	}
}
