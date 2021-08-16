package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int[][] firstMatrix = getMatrix(scanner);
//        int[][] secondMatrix = getMatrix(scanner);
//        try {
//            int[][] sum = getSumMatrices(firstMatrix, secondMatrix);
//            printMatrix(sum);
//        } catch (ArithmeticException e) {
//            System.out.println(e.getMessage());
//        }
        int scalar = scanner.nextInt();
        int[][] scaledMatrix = getScaledMatrix(firstMatrix, scalar);
        printMatrix(scaledMatrix);
    }

    public static int[][] getSumMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        int[][] sumMatrix = new int[firstMatrix.length][firstMatrix[0].length];
        if (firstMatrix.length != secondMatrix.length && firstMatrix[0].length != secondMatrix[0].length) {
            throw new ArithmeticException("\nERROR");
        }
        for (int row = 0; row < firstMatrix.length; row++) {
            for (int column = 0; column < firstMatrix[0].length; column++) {
                sumMatrix[row][column] = firstMatrix[row][column] + secondMatrix[row][column];
            }
        }
        return sumMatrix;
    }

    public static int[][] getScaledMatrix(int[][] matrix, int scalar) {
        int[][] scaledMatrix = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                scaledMatrix[row][column] =  scalar * matrix[row][column];
            }
        }
        return scaledMatrix;
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
