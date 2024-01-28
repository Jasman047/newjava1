import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class matrix {
    public static void main(String[] args) throws Exception {
        int rows = 3, cols = 2;
        int[][] matrix1 = new int[rows][cols];
        String filepath = "matrix1.txt";
        initializeMatrix(matrix1);
        writeMatrixToFile(matrix1, filepath);
        assert areArraysEqual(matrix1, readMatrixFromFile(filepath));
        int[][] matrix2 = new int[cols][rows];
        initializeMatrix(matrix2);
        writeMatrixToFile(matrix2, "matrix2.txt");
        int[][] result = multiplyMatrix(matrix1, matrix2);
        writeMatrixToFile(result, "result.txt");
        printMatrix(matrix1);
        printMatrix(matrix2);
        printMatrix(result);
    }

    public static boolean areArraysEqual(int[][] array1, int[][] array2) {
        if (array1.length != array2.length || array1[0].length != array2[0].length) {
            return false;
        }

        int rows = array1.length;
        int cols = array1[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (array1[row][col] != array2[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void initializeMatrix(int[][] matrix) {
        Random random = new Random();
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Initialize the matrix with random values
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) { 
                matrix[row][col] = random.nextInt(5);
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Print the matrix values
        for (int row = 0; row < rows; row++) {
            System.out.print("[");
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col]);
                if (col < cols - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    public static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        // Check if the matrices can be multiplied
        if (cols1 != rows2) {
            System.out.println("The matrices cannot be multiplied!");
            return null;
        }

        // Create the result matrix
        int[][] result = new int[rows1][cols2];

        // Multiply the matrices
        for (int row = 0; row < rows1; row++) {
            for (int col = 0; col < cols2; col++) {
                for (int i = 0; i < cols1; i++) {
                    result[row][col] += matrix1[row][i] * matrix2[i][col];
                }
            }
        }

        return result;
    }
    

    public static int[][] readMatrixFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int rows = 0;
        int cols = 0;

        // Determine the number of rows and columns in the matrix
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(" ");
            cols = elements.length;
            rows++;
        }

        // Reset the reader to read from the beginning of the file
        reader.close();
        reader = new BufferedReader(new FileReader(filePath));

        // Create the matrix with the determined size
        int[][] matrix = new int[rows][cols];

        // Read the matrix values from the file
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(" ");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(elements[col]);
            }
            row++;
        }

        reader.close();
        return matrix;
    }

    public static void writeMatrixToFile(int[][] matrix, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        // Write the matrix values to the file
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                writer.write(matrix[row][col] + " ");
            }
            writer.newLine();
        }

        writer.close();
    }
}