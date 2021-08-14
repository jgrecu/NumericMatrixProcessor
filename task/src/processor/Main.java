package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int[][] firstMatrix = getMatrix(scanner);
        printMatrix(firstMatrix);
    }

    private static int[][] getMatrix(Scanner scanner) {
        int rows1 = scanner.nextInt();
        int columns1 = scanner.nextInt();
        int[][] matrix = new int[rows1][columns1];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns1; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
