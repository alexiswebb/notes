public class Sort {

  public static void main(String[] args) {
    int[] array = { 20, 35, -15, 7, 55, 1, -22 };
    int[] wholeArray = { 2, 5, 9, 8, 2, 8, 7, 10, 4, 3 };

    //bubbleSort(array);
    //selectionSort(array);
    //insertionSort(array);
    //shellSort(array);
    //System.out.println("Factorial of 7: " + recursiveFactorial(7));
    //mergeSort(array, 0, array.length);
    //quickSort(array, 0, array.length);
    countingSort(wholeArray, 1, 10);
  }

  public static void countingSort(int[] array, int min, int max) {
    int[] tallyArray = new int[max-min+1];

    for (int i = 0; i < array.length; i++) {
      tallyArray[array[i] - min]++;
    }
    System.out.println("TALLY ARRAY:");
    printArray(tallyArray);

    int j = 0; 
    for (int i = min; i <= max; i++) {
      while (tallyArray[i-min] > 0) {
        array[j++] = i;
        tallyArray[i-min]--;
        System.out.println("transpose array from counting array");
        printArray(array);
      }
    }
    printArray(array);
  }

  public static void quickSort(int[] array, int start, int end) {
    if (end - start < 2) {
      // one element array
      return;
    }

    int pivotIndex = findPivotIndex(array, start, end);
    quickSort(array, start, pivotIndex);
    quickSort(array, pivotIndex+1, end);
  }

  public static int findPivotIndex(int[] array, int start, int end) {
    int pivot = array[start];
    int i = start;
    int j = end;

    // 'braid' in values; i increases and j decreases
    while (i < j) {
      while (i < j && array[--j] >= pivot);
      // move elements from left to right if less than pivot
      // eg. if the while loop above is escaped and i hasn't crossed j
      if (i < j) {
        array[i] = array[j];
      }

      while (i < j && array[++i] <= pivot); 
      // move elements from right to left if greater than pivot
      // eg. if the while loop above is escaped and i hasn't crossed j
      if (i < j) {
        array[j] = array[i];
      }
    }
    // re-insert the pivot into the array
    array[j] = pivot;
    printArray(array);
    return j;
  }
 
  public static void mergeSort(int[] array, int start, int end) {
    if (end - start < 2) {
      return;
    }

    //partition logically
    int mid = (start + end) / 2;
    mergeSort(array, start, mid);
    mergeSort(array, mid, end);
    merge(array, start, mid, end);
    printArray(array);
  }

  public static void merge(int[] array, int start, int mid, int end) {
    if (array[mid - 1] <= array[mid]) {
      return;
    }

    int i = start;
    int j = mid;
    int tempIndex = 0;
    int[] temp = new int[end - start];

    while (i < mid && j < end) {
       temp[tempIndex++] = array[i] <= array[j] ? array[i++] : array[j++];
    }
    // Handle left over elements if any exist in left array by copyinf left-sde items from i to (mid-i) at the point of start+tempIndex, which is the end of the sorted merged array
    System.arraycopy(array, i, array, start + tempIndex, mid-i);
    // If there are left over elements in the right array, they aren't copied because we stop the copy at tempIndex -- they're in order already
    System.arraycopy(temp, 0, array, start, tempIndex);
  }

  public static void shellSort(int[] array) {
    System.out.println("SHELL SORT");
    printArray(array);
    int traversal = 0;
    for (int gap = array.length/2; gap > 0; gap /= 2) {
      traversal++;
      System.out.println("Traversal " + traversal);
      for (int i = gap; i < array.length; i++) {
        int insertedElement = array[i];

        int j = i;
        while (j >= gap && array[j - gap] > insertedElement) {
          array[j] = array[j - gap];
          printArray(array);
          j -= gap;
         }
        array[j] = insertedElement;
      }
    }
    printArray(array);
  }

  public static void insertionSort(int[] array) {
    System.out.println("INSERTION SORT");
    printArray(array);
    for (int firstUnsortedIndex = 1; firstUnsortedIndex < array.length; firstUnsortedIndex++) {
      int insertedElement = array[firstUnsortedIndex];
      int i;
      
      System.out.println("Traversal " + firstUnsortedIndex);
      for (i = firstUnsortedIndex; i > 0 && array[i - 1] > insertedElement; i--) {
        array[i] = array [i-1];
      }
      array[i] = insertedElement;
      printArray(array);
    }
  }

  public static void selectionSort(int[] array) {
    System.out.println("SELECTION SORT");
    printArray(array);
    for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex --) {
      int largest = 0;
      
      System.out.println("Traversal " + (array.length - lastUnsortedIndex));
      for (int i = 1; i <= lastUnsortedIndex; i++){
        if (array[i] > array[largest]) {
          largest = i;
        } 
      } 
      swap(array, largest, lastUnsortedIndex);
      printArray(array);
    }
  }

  public static void bubbleSort(int[] array) {
    System.out.println("BUBBLE SORT");
    printArray(array);
    for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex -- ) {
      System.out.println("Traversal " + (array.length - lastUnsortedIndex));
      for (int i = 0; i < lastUnsortedIndex; i++) {
        if (array[i] > array[i+1]) {
          swap(array, i, i+1);
          printArray(array);
        }
      }
    }
    printArray(array);
  }

  public static void swap(int[] array, int indexA, int indexB) {
    if (indexA == indexB) {
      return;
    }
    
    System.out.println("swapping items!");
    int temp = array[indexA];
    array[indexA] = array[indexB];
    array[indexB] = temp;
  }

  public static void printArray(int[] array) {
    System.out.println("array: ");
    for(int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    } 
  }

  public static int iterativeFactorial(int num) {
    if (num == 0) {
      return 1;
    }
    
    int factorial = 1;
    for (int i = 1; i <= num; i++) {
      factorial *= i;
    }

    return factorial;
  }

  public static int recursiveFactorial(int num) {
    if (num == 0) {
      return 1;
    }
    
    return num * recursiveFactorial(num-1);
  }
}
