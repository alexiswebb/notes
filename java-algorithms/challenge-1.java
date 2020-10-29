// Challenge: modify merge algorithm to sort in reverse order

public class Challenge1 {

  public static void main(String[] args) {
    int[] array = { 20, 35, -15, 7, 55, 1, -22 };
    System.out.println("REVERSE MERGE SORT");
    printArray(array);
    reverseMergeSort(array, 0, array.length);
    printArray(array);
  }

  public static void reverseMergeSort(int[] array, int start, int end) {
    if (end-start < 2) {
      return;
    }

    int mid = (start + end) / 2;
    reverseMergeSort(array, start, mid);
    reverseMergeSort(array, mid, end);
    reverseMerge(array, start, mid, end);
  }

  public static void reverseMerge(int[] array, int start, int mid, int end) {
    if (array[mid] <=  array[mid-1]) {
      return;
    }
   
    int i = start;
    int j = mid;
    int tempIndex = 0;
    int[] tempArray = new int[end-start];

    while(i < mid && j < end) {
      // take the largest value and move it into temp
      tempArray[tempIndex++] = array[i] < array[j] ? array[j++] : array[i++];
      printArray(tempArray);
    }
    // if i has remaining large numbers, they need to be moved to the back
    System.arraycopy(array, i, array, start + tempIndex, mid-i);
    // copy temp over to array, leaving remaining j numbers (if any) - they're sorted. 
    System.arraycopy(tempArray, 0, array, start, tempIndex);
  }

  public static void printArray(int[] array) {
    System.out.println("array: ");
    for(int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    } 
  }
}
