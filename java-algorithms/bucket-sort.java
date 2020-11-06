import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class BucketSort {
  public static void main(String[] arg) {
    int[] intArray = { 54, 46, 13, 66, 35, 92, 43, 27, 8 };

    bucketSort(intArray);
    printArray(intArray);
  }

  public static void bucketSort(int[] array) {
    List<Integer>[] buckets = new List[10];

    for (int i = 0; i < buckets.length; i++) {
      // can be LinkedList or Vector instead of ArrayList
      buckets[i] = new ArrayList<Integer>();
    }

    for (int i = 0; i < array.length; i++) {
      int hashValue = hash(array[i]);
      buckets[hashValue].add(array[i]); 
    }

    for (List bucket: buckets) {
      Collections.sort(bucket);
    }

    int j = 0;
    for (int i = 0; i < buckets.length; i++) {
      for (int value:  buckets[i]) {
	array[j++] = value;
      }
    }
  }
 
  private static int hash(int value) {
     System.out.println(value + " is hashed as " + (value / (int) 10));
     return value / (int) 10;
  }
  
  public static void printArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
  }
}
