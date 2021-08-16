package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int[][] firstMatrix = getMatrix(scanner);
        int[][] secondMatrix = getMatrix(scanner);
        try {
            int[][] sum = getSumMatrices(firstMatrix, secondMatrix);
            printMatrix(sum);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int[][] getSumMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        int[][] sumMatrix = new int[firstMatrix.length][firstMatrix[0].length];
        if (firstMatrix.length != secondMatrix.length && firstMatrix[0].length != secondMatrix[0].length) {
            throw new ArithmeticException("\nERROR");
        } else {
            for (int row = 0; row < firstMatrix.length; row++) {
                for (int column = 0; column < firstMatrix[0].length; column++) {
                    sumMatrix[row][column] = firstMatrix[row][column] + secondMatrix[row][column];
                }
            }
        }
        return sumMatrix;
    }

    public static int[][] getMatrix(Scanner scanner) {
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println();
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.printf("%d ", cell);
            }
            System.out.println();
        }
    }
}
