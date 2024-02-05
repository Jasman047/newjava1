import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class bubbleSort {

    public static void main(String[] args) {
        int arrayLength = 0;
        String inputFilename = "";
        String outputFilename = "";

        // Get user input for array length
        while (true) {
            System.out.print("Enter the length of the array: ");
            try {
                arrayLength = Integer.parseInt(System.console().readLine());
                if (arrayLength > 0) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }

        // Create a random array
        int[] array = createRandomArray(arrayLength);
        System.out.println("Created random array: " + Arrays.toString(array));

        // Write the array to a file
        inputFilename = "randomArray.txt";
        writeArrayToFile(array, inputFilename);
        System.out.println("Array written to file: " + inputFilename);

        // Read the array from the file
        int[] readArray = readFileToArray(inputFilename);
        System.out.println("Read array from file: " + Arrays.toString(readArray));

    
    }

    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (int i : array) {
                fileWriter.write(String.valueOf(i) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    public static int[] readFileToArray(String filename) {
        int[] array = new int[0];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                array = Arrays.copyOf(array, array.length + 1);
                array[array.length - 1] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + filename);
        }
        return array;
    }

    public static void bubbleSort(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}