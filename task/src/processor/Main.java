package processor;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String THE_RESULT_IS = "The result is:";

    public static void main(String[] args) {
        matrixOperationsMenu();
    }

    public static double[][] getMatrix() {
        System.out.print("Enter size of the matrix : ");
        int rows = SCANNER.nextInt();
        int columns = SCANNER.nextInt();
        double[][] matrix = new double[rows][columns];
        System.out.println("Enter matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = SCANNER.nextDouble();
            }
        }
        return matrix;
    }

    public static double[][] getMatrix(int x) {
        String ord = x == 1 ? "first":"second";
        System.out.print("Enter the size of the "+ ord + " matrix : ");
        int rows = SCANNER.nextInt();
        int columns = SCANNER.nextInt();
        double[][] matrix = new double[rows][columns];
        System.out.println("Enter " + ord + " matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = SCANNER.nextDouble();
            }
        }
        return matrix;
    }

    public static void printMatrix(double[][] matrix) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println();
        for (double[] row : matrix) {
            for (double cell : row) {
                System.out.printf("%s ", df.format(cell));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static double[][] sumMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] sumMatrix = new double[firstMatrix.length][firstMatrix[0].length];
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

    public static double[][] scaleMatrix(double[][] matrix, double scalar) {
        double[][] scaledMatrix = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                scaledMatrix[row][column] =  scalar * matrix[row][column];
            }
        }
        return scaledMatrix;
    }

    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
        return result;
    }

    private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public static double[][] transposeMatrixMainDiagonal(double[][] matrix) {
        final int N = matrix[0].length;
        final int M = matrix.length;
        double[][] result = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public static double[][] verticalReflection(double[][] matrix) {
        final int N = matrix[0].length;
        final int M = matrix.length;
        double[][] result = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[j][i] = matrix[j][N - (i + 1)];
            }
        }
        return result;
    }

    public static double[][] horizontalReflection(double[][] matrix) {
        final int N = matrix[0].length;
        final int M = matrix.length;
        double[][] result = new double[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = matrix[M - (i + 1)][j];
            }
        }
        return result;
    }

    public static double[][] transposeMatrixSideDiagonal(double[][] matrix) {
        final int N = matrix[0].length;
        final int M = matrix.length;
        double[][] result = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result[j][i] = matrix[N - i - 1][M - j - 1];
            }
        }
        return result;
    }

    public static double calculateDeterminant(double[][] matrix) {
        double determinant = 0;
        final int N = matrix[0].length;
        final int M = matrix.length;
        if (M == 1) {
            return matrix[0][0];
        }

        double[][] temp = new double[N][M];

        return determinant;
    }

    public static void matrixOperationsMenu() {
        double[][] firstMatrix;
        double[][] secondMatrix;
        double[][] result;
        boolean isOn = true;
        while (isOn) {
            System.out.println("1. Add matrices\n" +
                    "2. Multiply matrix by a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "0. Exit\n" +
                    "Your choice: ");
            int choice = SCANNER.nextInt();
            switch (choice) {
                case 1:
                    firstMatrix = getMatrix(1);
                    secondMatrix = getMatrix(2);
                    try {
                        result = sumMatrices(firstMatrix, secondMatrix);
                        System.out.println(THE_RESULT_IS);
                        printMatrix(result);
                    } catch (ArithmeticException e) {
                        System.out.println("The operation cannot be performed.\n");
                    }
                    break;
                case 2:
                    firstMatrix = getMatrix();
                    System.out.print("Enter constant: ");
                    double scalar = SCANNER.nextDouble();
                    result = scaleMatrix(firstMatrix, scalar);
                    System.out.println(THE_RESULT_IS);
                    printMatrix(result);
                    break;
                case 3:
                    firstMatrix = getMatrix(1);
                    secondMatrix = getMatrix(2);
                    result = multiplyMatrices(firstMatrix, secondMatrix);
                    System.out.println(THE_RESULT_IS);
                    printMatrix(result);
                    break;
                case 4:
                    transposeMatrixSubMenu();
                    break;
                case 0:
                    isOn = false;
                    break;
                default:
                    break;
            }
        }
    }

    private static void transposeMatrixSubMenu() {
        double[][] matrix;
        double[][] result;
        boolean isOn = true;
        while (isOn) {
            System.out.println("1. Main diagonal\n" +
                    "2. Side diagonal\n" +
                    "3. Vertical line\n" +
                    "4. Horizontal line\n" +
                    "Your choice: ");
            int choice = SCANNER.nextInt();
            switch (choice) {
                case 1:
                    matrix = getMatrix();
                    result = transposeMatrixMainDiagonal(matrix);
                    System.out.println(THE_RESULT_IS);
                    printMatrix(result);
                    isOn = false;
                    break;
                case 2:
                    matrix = getMatrix();
                    result = transposeMatrixSideDiagonal(matrix);
                    System.out.println(THE_RESULT_IS);
                    printMatrix(result);
                    isOn = false;
                    break;
                case 3:
                    matrix = getMatrix();
                    result = verticalReflection(matrix);
                    System.out.println(THE_RESULT_IS);
                    printMatrix(result);
                    isOn = false;
                    break;
                case 4:
                    matrix = getMatrix();
                    result = horizontalReflection(matrix);
                    System.out.println(THE_RESULT_IS);
                    printMatrix(result);
                    isOn = false;
                    break;
                default:
                    break;
            }
        }
    }

}
