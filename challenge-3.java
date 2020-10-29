// Challenge: Use radix sort to sort five-letter strings

public class Challenge3 {

  public static void main(String[] args) {
    String[] array = { "bcdef", "omadd", "bbbbb", "dbaqc", "abcde" };
    System.out.println("RADIX STRING SORT");

    printArray(array);
    radixSort(array, 26, 5);
    printArray(array);
  }

  public static void radixSort(String[] array, int radix, int width) {
    for (int i = width-1; i >= 0; i--) {
      radixSingleSort(array, i, radix);
      System.out.println("Ordering the character in position " + i);
      printArray(array);
    }    
  }

  public static void radixSingleSort(String[] array, int position, int radix) {
    int arrayLength = array.length;
    int[] tallyArray = new int[radix];

    for (String word: array) {
      tallyArray[getIndex(position, word)]++;
    }

    // Adjust tally array to show occurances of all values <= index value
    for (int j = 1; j < radix; j++) {
      tallyArray[j] += tallyArray[j-1];
    }

    // copy into temporary array working right to left
    String[] temp = new String[arrayLength];
    for (int tempIndex = arrayLength - 1; tempIndex >= 0; tempIndex--) {
      int characterIndex = getIndex(position, array[tempIndex]);
      temp[--tallyArray[characterIndex]] = array[tempIndex];
    }
    for (int i = 0; i < arrayLength; i++) {
      array[i] = temp[i];
    }
  }

  public static int getIndex(int position, String word) {
    return word.charAt(position) - 'a';
  }

  public static void printArray(String[] array) {
    System.out.println("array: ");
    for(int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    } 
  }
}
