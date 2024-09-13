import java.util.Random;

import static java.lang.System.nanoTime;

public class MergeSort {

    //insertion sort
    public static int insertionSort(int[] array, int n, int m) {
        /*
        if (array == null || n < 0 || m >= array.length || n >= m) {
            throw new IllegalArgumentException("Invalid indices or array.");
        }
         */
        int count = 0;

        for (int i = n + 1; i <= m; i++) {
            for(int j=i; j>n; j--){
                count++;
                if (array[j] < array[j-1]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
                else
                    break;
            }
        }
        return count;
    }

    // Merge function
    public static int mergeOriginal(int[] arr, int n, int m) {
        int mid = (n + m) / 2;
        if (m - n < 0) {
            return 0;
        }

        int keyComparisons = 0;
        int list1 = n, list2 = mid + 1;

        while (list1 <= mid && list2 <= m) {
            keyComparisons++;

            // Left subarray element is greater than right subarray element
            if (arr[list1] > arr[list2]) {
                // Temporarily store element of right subarray
                int temp = arr[list2];
                list2++;
                mid++; // Shift mid to right subarray to make space for the smaller element

                // Shift elements to the right to make space for the smaller element
                for (int i = mid; i > list1; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[list1] = temp;
                list1++;

                // If left subarray is smaller, just move to the next element
            } else if (arr[list1] < arr[list2]) {
                list1++;

                // If both subarray elements are equal
            } else {
                if (list1 == mid && list2 == m) {
                    break;
                }

                // Insert right element in the correct position in the left half
                int temp = arr[list2];
                list2++;
                list1++;
                mid++;

                for (int i = mid; i > list1; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[list1] = temp;
                list1++;
            }
        }

        return keyComparisons;
    }

    // MergeSort function
    public static int mergeSortOriginal(int[] arr, int n, int m) {
        int mergeCount = 0;
        int leftCount = 0;
        int rightCount = 0;
        int mid = (n + m) / 2;

        if ((m - n) <= 0) {
            return 0;
        } else {
            // Recursively sort first half
            leftCount = mergeSortOriginal(arr, n, mid);

            // Recursively sort second half
            rightCount = mergeSortOriginal(arr, mid + 1, m);

            // Merge the two lists after recursive call
            mergeCount = mergeOriginal(arr, n, m);
        }

        return leftCount + rightCount + mergeCount;
    }

    public static int mergeSortOriginal(int[] arr, int n, int m, int S) {
        int mergeCount = 0;
        int leftCount = 0;
        int rightCount = 0;
        int mid = (n + m) / 2;

        if ((m - n) <= S) {
            // Use insertion sort for subarrays smaller than or equal to size S
            mergeCount = insertionSort(arr, n, m);
            return mergeCount; // No merge count for insertion sort
        } else {
            // Recursively sort first half
            leftCount = mergeSortOriginal(arr, n, mid, S);

            // Recursively sort second half
            rightCount = mergeSortOriginal(arr, mid + 1, m, S);

            // Merge the two lists after recursive call
            mergeCount = mergeOriginal(arr, n, m);
        }

        return leftCount + rightCount + mergeCount;
    }

    // Main function for testing
    public static void main(String[] args) {
        int[] arr = generateDataset(10000000);


        int n = 0;
        int m = arr.length - 1;

        double startTime = nanoTime();
        int comparisons = mergeSortOriginal(arr.clone(), n, m, 0);
        double endTime = nanoTime();
        double timeElapsed = (endTime - startTime) / 1000000;

        System.out.println("\nMergeSort Key Comparisons: " + comparisons);
        System.out.println("Time Elapsed: " + timeElapsed + "ms");

        startTime = nanoTime();
        comparisons = mergeSortOriginal(arr.clone(), n, m, 6);
        endTime = nanoTime();
        timeElapsed = (endTime - startTime) / 1000000;

        System.out.println("\nHybrid Sort Key Comparisons, S = 6: " + comparisons);
        System.out.println("Time Elapsed: " + timeElapsed + "ms");
    }

    // Generating dataset
    public static int[] generateDataset(int size){
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++){
            arr[i] = random.nextInt(10000000);
        }
        return arr;
    }



}