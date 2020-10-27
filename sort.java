public class Sort {

  public static void main(String[] args) {
    int[] array = { 20, 35, -15, 7, 55, 1, -22 };

    //bubbleSort(array);
    //selectionSort(array);
    //insertionSort(array);
    //shellSort(array);
    System.out.println("Factorial of 7: " + recursiveFactorial(7));
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
