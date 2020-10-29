// Challenge: write an insertion sort that uses recursion

public class Challenge2 {

  public static void main(String[] args) {
    int[] array = { 20, 35, -15, 7, 55, 1, -22 };
    System.out.println("RECURSIVE INSERTION SORT");

    printArray(array);
    insertionSort(array, array.length);
    printArray(array);
  }

  public static void insertionSort(int[] array, int numberOfItems) {
    if (numberOfItems < 2) {
      return;
    }

    insertionSort(array, numberOfItems-1);

    int insertedValue = array[numberOfItems-1];
    int i;
    for (i = numberOfItems - 1; i > 0 && array[i - 1] > insertedValue; i--){
       array[i] = array[i-1];
    }
    array[i] = insertedValue;
  }

  public static void printArray(int[] array) {
    System.out.println("array: ");
    for(int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    } 
  }
}
