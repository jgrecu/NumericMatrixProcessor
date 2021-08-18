package processor;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String THE_RESULT_IS = "The result is:";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

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
        System.out.println();
        for (double[] row : matrix) {
            for (double cell : row) {
                System.out.printf("%s ", DECIMAL_FORMAT.format(cell));
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

    /* Recursive function for finding determinant of matrix. n is current dimension of matrix[][]. */
    public static double calculateDeterminant(double[][] matrix, int n) {
        double determinant = 0;
        final int M = matrix[0].length;
        if (n != M) {
            System.out.println ("The matrix is not a square matrix, there is no determinant");
            return Double.MIN_VALUE;
        }
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double[][] temp = new double[M][M];
        int sign = 1;
        for (int i = 0; i < n; i++) {
            getCofactor(matrix, temp, 0, i, n);
            determinant += sign * matrix[0][i] * calculateDeterminant(temp, n - 1);
            sign = -sign;
        }

        return determinant;
    }

    private static void getCofactor(double[][] mat, double[][] temp, int p, int q, int n) {
        // Function to get cofactor of mat[p][q] in temp[][]. n is current dimension of mat[][]
        int i = 0;
        int j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Copying into temporary matrix only those elements which are not in given row and column
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    // Row is filled, so increase row index and reset col index
                    if (j == n -1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
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
                    "5. Calculate a determinant\n" +
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
                case 5:
                    firstMatrix = getMatrix();
                    double determinant = calculateDeterminant(firstMatrix, firstMatrix.length);
                    System.out.println(THE_RESULT_IS);
                    System.out.printf("%s%n%n", DECIMAL_FORMAT.format(determinant));
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
