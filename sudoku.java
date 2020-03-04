import java.util.Scanner;

public class sudoku {
	private static final int Size = 3;
	private static final int[][] board = new int[Size][Size];

	private static void readBoard() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sudoku Game");
		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				System.out.printf("Enter number for row %d and column %d: ", i + 1, j + 1);
				board[i][j] = sc.nextInt();
			}
		}
		sc.close();
	}

	private static void displayBoard() {
		System.out.println("Sudoku Board");
		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void isValidBoard() {
		boolean[] numbers = new boolean[9];
		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				if(board[i][j] < 1 || board[i][j] > 9) {
					System.out.printf("Value %d at row %d and column %d is invalid. Sudoku is invalid.", board[i][j], i, j);
					return;
				}
				if(numbers[board[i][j]-1]) {
					System.out.printf("Value %d at row %d and column %d already used. Sudoku is invalid.", board[i][j], i, j);
					return;
				}
				numbers[board[i][j]-1]=true;
			}
		}
		int rowSum = getRowSum(0);
		for (int i =0; i < Size; i++) {
			if(getRowSum(i) != rowSum) {
				System.out.printf("Row sum for row %d is invalid. Sudoku is invalid.", i);
				return;
			}
			if(getColumnSum(i) != rowSum) {
				System.out.printf("Column sum for column %d is invalid. Sudoku is invalid.", i);
				return;
			}
		}
		if(getForwardDiagonalSum() != rowSum) {
			System.out.printf("Forward diagonal sum is invalid. Sudoku is invalid.");
			return;
		}
		if(getReverseDiagonalSum() != rowSum) {
			System.out.printf("Reverse diagonal sum is invalid. Sudoku is invalid.");
			return;
		}
		System.out.println("Sudoku is valid.");
	}
	
	private static int getRowSum(int row) {
		int sum = 0;
		for (int i =0; i < Size; i++) {
			sum += board[row][i];
		}
		return sum;
	}

	private static int getColumnSum(int column) {
		int sum = 0;
		for (int i =0; i < Size; i++) {
			sum += board[i][column];
		}
		return sum;
	}

	private static int getForwardDiagonalSum() {
		int sum = 0;
		for (int i =0; i < Size; i++) {
			sum += board[i][i];
		}
		return sum;
	}

	private static int getReverseDiagonalSum() {
		int sum = 0;
		for (int i =0; i < Size; i++) {
			sum += board[Size-i-1][i];
		}
		return sum;
	}

	public static void main(String[] args) {
		readBoard();
		displayBoard();
		isValidBoard();
	}
}