import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickSelectManager {
    //
public static void main(String[] args) {
// DO NOT CHANGE ANYTHING IN THIS FUNCTION
if (args.length == 0) {
throw new IllegalArgumentException("CSV file path must be provided as a command-line argument");
}
QuickSelectManager quickSelectManager = new QuickSelectManager();
quickSelectManager.executeFromCSV(args[0]);
// Example method to call it from the .jar file: "java -jar QuickSelectManager.jar
// ExampleInputW4.csv".
}

    public QuickSelectManager() {
    }

     public Integer quickSelect(int[] A, int k) {
        Integer result = null;

        if (k < 1 || k > A.length) {
            return result;
        }

        int[] copy = A.clone();//make a copy
        result = quickSelectHelper(copy, 0, copy.length - 1, k);
        return result;
    }

    
    private int quickSelectHelper(int[] A, int low, int high, int k) {
        if (low == high) {
            return A[low];
        }

        int pivotIndex = partition(A, low, high);
        int rank = pivotIndex - low + 1; // rank basically to check if it wants the smallest before or after it 

        if (k == rank) {
            return A[pivotIndex];
        } else if (k < rank) {//go left the pivot
            return quickSelectHelper(A, low, pivotIndex - 1, k);
        } else {//go right the pivot 
            return quickSelectHelper(A, pivotIndex + 1, high, k - rank);
        }
    }

    private int partition(int[] A, int low, int high) {
        int pivot = A[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (A[j] <= pivot) {
                i++;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }

        // Place pivot in its correct position
        int tmp = A[i + 1];
        A[i + 1] = A[high];
        A[high] = tmp;

        return i + 1;
    }

    public void executeFromCSV(String filePath) {
        // DO NOT CHANGE ANYTHING IN THIS FUNCTION
        // Example csv is available
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                String[] tokens = line.split(",");
                String[] arrayElements = tokens[0].trim().split(";");
                String kString = tokens[1].trim().toUpperCase();
                int[] A = new int[arrayElements.length];
                for (int i = 0; i < arrayElements.length; i++) {
                    A[i] = Integer.parseInt(arrayElements[i]);
                }
                int k = Integer.parseInt(kString);
                Integer res = quickSelect(A, k);
                System.out.print("A: ");
                for (int e : A)
                    System.out.print(e + ";");
                System.out.println("\nk: " + k);
                System.out.println("Result: " + res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
