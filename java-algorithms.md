# JAVA ALGORITHMS

## Big-O Notation

Time complexity: number of steps involved in an algorithm
Memory complexity: amount of memory required to run an algorithm

Big-O measures "worst case" / upper bound of complexity

* Constant - O(1): O of 1, constant running rate
* Logarithmic - O(logn): log to base 2
* Linear - O(n): the value of n determines the number of steps; Example is 'adding sugar to tea' where adding sugar takes two steps and there are two other steps in the 'tea' algorithm. Thus, the runtime of a the algorithm is 2n+2. both 2() and +2 are constant, only the n influences the runtime.
* n log n - O(nlogn):
* Quadratic - O(n^2): 
* O(2^n):
* Factorial - O(n!):


## Arrays

All items in an array are stored in a continguous block of memory
* In java, once an array size is declared, it cannot be changed - Arrays aren't dynamic types *

All items in an array occupy the same amount of space in memory
Object references are the same size, regardless of the type of object they refer to.
This makes it easy to calculate the memory address of items in the array using index: (location of the first array element) + index * (size of each element)
* Note: usng 0-based indices means that the first element in an array is the starting location in memory eg. (memory address) + 0*size = memory address. Otherwise all the elements would be offset by size.
* Accessing an array item is of Constant Complexity (O(1)), because we simply use the index to retrieve the element from memory; the length of the array doesn't influence the complexity of retrieval at all.
Searching through the array if you don't know the index requires looking at each item - this is Linear Complexity (O(n))

* Retrieval with index = O(1)
* Retrieval without index = O(n)
* Adding to a full array = O(n); arrays are static, so we need to copy the array to a new array of length+1, which requires looping over the array elements.
* Adding to the end of an array with room = O(1)
* Inserting/updating an element = O(1)
* Deleting an element by setting to null = O(1)
* Deleting by shifting = O(n), because all items need to be moved

## Sort Algorithms

### Bubble sort: As you move through an array, partion into sorted and unsorted. Begin with an 'unsortedPartitionIndex' which is the last index of the unsorted partition. Compare array[0] with array[1] and if array[0] is greater, swap them, otherwise move to compare array[1] with array[2] and so forth. Inevitably, after the first traversal, the largest number will be at the end, so the 'unsortedPartitionIndex' is now one less. Go through another traversal up to array[n-1], then array[n-2] etc until you're left with only one element. Now the list is sorted. Because you need to go through a number of traversals equal to the array length it has quadratic complexity
* Summary: compare and swap all adjacent unsorted elements each traversal.
* In-place algorithm: no need to create a new array to place sorted items
* O(n^2) complexity: it takes 100 steps to sort 10 items, 10,000 for 100 items... the implementation uses nested loops - each loop usually indicated complexity of n, so a nested loop will often lead to n^2 complexity
* Stable sort: once the duplicates are next to each other, the left-most will remain the left-most because it only swaps if the left-most item is GREATER, not GREATER/EQUAL

"Stable vs Unstable Sorts"
When you hvae duplicate values, an unstable sort doesn't preserve the original order of duplicate values. In stable sorts, the original order of duplicate values will be preserved.

### Selection Sort: traverse array (compare array[i] to array [i+1] and track the index of the largest number, continue through array)  to find the largest value and swap it with the element at the end; continue with the elements remaining (one less than the starting length);
* Summary: find largest and swap it each traversal
* In-pace algorithm
* O(n^2) complexity, but swaps less than bubble (only one swap per traversal)
* Unstable sort!

### Insertion Sort: element at 0 is sorted; unsorted partition is everything else. Over each traversal, insert the first unsorted item into the sorted parition. Insertions compare the value you want to insert by comparing values in the sorted partition, moving right to left. Once you find a value that is less than or greater than the insertion value, insert it. This requires shifting greater values to the right one space for each traversal.
* Summary: insert next element into sorted partition, shifting elements right
* In-place algorithm
* O(n^n) complexity
* Stable = inserting from left to right, so once the right-most duplicate comes up, it will be inserted to the right of the first duplicate value.

### Shell Sort: a variation on Insertion Sort; determine a gap - using this gap, select the element at index = gap and compare this to the element a distance = gap to the left. If it is less, swap the elements and continue. Once the element is greater than or equal to the element a gap away on the left, select the next item at array[gap+1]. Once the end of the array is reached, the gap gets smaller until the gap is zero, which will lead to an insertion sort. This makes the insertion sort more performant because fewer swaps will occur in a partially-sorted array.
* Summary: swap across gaps each traversal; gap size will decrease and once it reached one, do an insertion sort
* In-place algorithm
* Worst case is still O(n^2), but performance can increase based on gap
* Unstable sort -- elements jump over each other according to gap
 
