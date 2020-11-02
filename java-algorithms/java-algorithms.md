# JAVA ALGORITHMS

## Big-O Notation

* Time complexity: number of steps involved in an algorithm
* Memory complexity: amount of memory required to run an algorithm

Big-O measures "worst case" / upper bound of complexity

* Constant - O(1): O of 1, constant running rate
* Logarithmic - O(logn): log to base 2
* Linear - O(n): the value of n determines the number of steps
* n log n - O(nlogn):
* Quadratic - O(n^2): 
* O(2^n):
* Factorial - O(n!):


## Arrays

* All items in an array are stored in a continguous block of memory
  * In java, once an array size is declared, it cannot be changed - Arrays aren't dynamic types
* All items in an array occupy the same amount of space in memory
  * Object references are the same size, regardless of the type of object they refer to. This makes it easy to calculate the memory address of items in the array using index: (location of the first array element) + index * (size of each element).
  * **Note:** using 0-based indices means that the first element in an array is the starting location in memory eg. (memory address) + 0*size = memory address. Otherwise all the elements would be offset by size.
* Accessing an array item is of Constant Complexity (O(1)), because we simply use the index to retrieve the element from memory; the length of the array doesn't influence the complexity of retrieval at all.
* Searching through the array if you don't know the index requires looking at each item - this is Linear Complexity (O(n))
***
* Retrieval with index = O(1)
* Retrieval without index = O(n)
* Adding to a full array = O(n); arrays are static, so we need to copy the array to a new array of length+1, which requires looping over the array elements.
* Adding to the end of an array with room = O(1)
* Inserting/updating an element = O(1)
* Deleting an element by setting to null = O(1)
* Deleting by shifting = O(n), because all items need to be moved

## Sort Algorithms

#### "Stable vs Unstable Sorts"
When you have duplicate values, an unstable sort doesn't preserve the original order of duplicate values. In stable sorts, the original order of duplicate values will be preserved.

#### Bubble sort 
As you move through an array, partion into sorted and unsorted. Begin with an 'unsortedPartitionIndex' which is the last index of the unsorted partition. Compare array[0] with array[1] and if array[0] is greater, swap them, otherwise move to compare array[1] with array[2] and so forth. Inevitably, after the first traversal, the largest number will be at the end, so the 'unsortedPartitionIndex' is now one less. Go through another traversal up to array[n-1], then array[n-2] etc until you're left with only one element. Now the list is sorted. Because you need to go through a number of traversals equal to the array length it has quadratic complexity
* Summary: compare and swap all adjacent unsorted elements each traversal.
* In-place algorithm: no need to create a new array to place sorted items
* O(n^2) complexity: it takes 100 steps to sort 10 items, 10,000 for 100 items... the implementation uses nested loops - each loop usually indicated complexity of n, so a nested loop will often lead to n^2 complexity
* Stable sort: once the duplicates are next to each other, the left-most will remain the left-most because it only swaps if the left-most item is GREATER, not GREATER/EQUAL

#### Selection Sort
Traverse array (compare array[i] to array [i+1] and track the index of the largest number, continue through array)  to find the largest value and swap it with the element at the end; continue with the elements remaining (one less than the starting length);
* Summary: find largest and swap it each traversal
* In-pace algorithm
* O(n^2) complexity, but swaps less than bubble (only one swap per traversal)
* Unstable sort!

#### Insertion Sort:
Element at 0 is sorted; unsorted partition is everything else. Over each traversal, insert the first unsorted item into the sorted parition. Insertions compare the value you want to insert by comparing values in the sorted partition, moving right to left. Once you find a value that is less than or greater than the insertion value, insert it. This requires shifting greater values to the right one space for each traversal.
* Summary: insert next element into sorted partition, shifting elements right
* In-place algorithm
* O(n^n) complexity
* Stable = inserting from left to right, so once the right-most duplicate comes up, it will be inserted to the right of the first duplicate value.

#### Shell Sort:
A variation on Insertion Sort; determine a gap - using this gap, select the element at index = gap and compare this to the element a distance = gap to the left. If it is less, swap the elements and continue. Once the element is greater than or equal to the element a gap away on the left, select the next item at array[gap+1]. Once the end of the array is reached, the gap gets smaller until the gap is zero, which will lead to an insertion sort. This makes the insertion sort more performant because fewer swaps will occur in a partially-sorted array.
* Summary: swap across gaps each traversal; gap size will decrease and once it reached one, do an insertion sort
* In-place algorithm
* Worst case is still O(n^2), but performance can increase based on gap
* Unstable sort -- elements jump over each other according to gap
 
#### Merge Sort
Take an array and logicially split the array in half, until you're left with (logical, not actual) arrays of only one element each. Then, merge left/right pairs into sorted temporary arrays that are then placed back into the original array; repeat until all pairs are sorted.
  * Uses i index to compare each element in a left pair to the j index in the right pair. If i or j reach the end of their array length before the other pair, the temp array is sorted because the remaining elements have already been sorted.
* Summary: halve and compare pairs to two, then pairs all the way up.
* Not in-place: this algorithm uses temporary arrays for the merge
* O(nlogn) - we halve the array recursively
* Stable algorithm: left duplicate is copied into sorted array first

#### Quick Sort
Makes a 'pivot' point in the array, moving all items lesser-than to the left, and greater-than to the right. You can find these values by alternating left and right values against the pivot value and swapping positions according to value against the pivot. For instance, start at index 0 as pivot and store in temp value. Compare to element at the end of the array, and if that element is less, place it in index 0 (overwritting the pivot, but the pivot value is saved in temp); continue with element at index 1 and then element at array.length-1, etc. Once the positions overlap, you've found the index for the pivot.
* Summary: put elements less than or more than a pivot value,  and then move pivot until every element has been sorted into the correct index.
* In-place algorithm
* O(nlogn) - we parition into halves
* Unstable algorithm: 'braiding' left and right means that original order of duplicate items won't be guarenteed

#### Counting Sort
Algorithm specifically for discrete, non-negative values - typically whole numbers, and within a 'smallish' range. 'Tally' the number of occurances of each value at the index = value-1; eg - if working with values between one and ten, make an array of length 10. If there are five twos in the data set, the value at index 4 (value-1, to account for zero index) will be equal to 5. Once all the values are accounted for, the count can be projected into an array by going through the 'tallied' array and outputing the number of each value (index+1), the number of times of the value (recording occurance number).
* Summary: transpose occurance count into an array where index indicates value; transpose back into an array that will now be ordered.
* Not in-place; there's a tallying array used to record values
* O(n): only requires one traversal, because we can make assumptions about data
* Unstable algorithm

#### Stable Counting Sort
Calculate where each value should go back into final array and write them in backwards order to retain the original order of duplicate values. First, transpose the array into a normal counting array where each value is 'tallied' in the index equal to the value (eg. (0, 1, 2, 2, 7) will become (1,1,2,0,0,0,0,1)), then modify the count in the counting array to be the number of values =< the current index - you can simply sum the values going right to left (eg. (1,1,2,0,0,0,0,1) becomes (1,2,4,4,4,4,4,5)). Now, going right to left through the original array, decrease the count array value at the index = original array value. This new value is the index where the original value should go in the final array. ((eg. 7 in the original array -> countingArray[7] = 5, decrement 5 to 4, 4 is the index position in the sorted array; nevermind that my example is already sorted :))
* Summary: Stable because of the summing on the counting array and writing backwards to the final array (right to left)

#### Radix Sort
Algorithm specifically for integers and strings. Radix represnts the number of distinct characters (eg. 10 for decimal, 26 for english alphabet). Data must all conform to the same length (eg. 5 letter words). Algorithms moves across the width of the objects (eg. sort 1s, then 10s; sort left to right for words), so the sort has to be stable to respect the sorting in iterations that came before.
* Summary: sorting same-length words/numbers, go character by character using counting sort
* Counting sort is often used for each iteration, but it has to be made stable
* O(n) - but requires a lot of memory because of the overhead of splitting the width of the number/word.
* Can be in-place (depends on implementation)

## Lists
Abstract data type - represented as an interface which is implemented by a variety of classes. 

#### Array Lists
Resizable array implementation of the list interface. Capacity is set, but if capacity is exceeded, the backing array will need to be transferred to a new, larger array - requiring memory. Unsynchronized: writes can create conflicts.

#### Vectors
Also a growable array, but thread-safe, unlike array lists. Synchronization has overhead, which can take memory (and time) - so the array list was added to the JDK to create an array-backed list with less overhead.

#### Linked Lists
Each item in the list is aware of another item in the list. For singly-linked lists, each list item is aware of the following item in the list, which means the value is stored as well as a reference to the next value.
* Head: first item in list
* Node: each item in the list
* Last item points to null
* To insert: create a new node and insert at the front of the list (so you don't need to traverse the list to place it); this is of constant complexity (O(1)); this new node references the old head node and becomes the new head node.
* To delete: assign node to a temp variable and change head to refer to the next element (if removing the first element); return temp. Also O(1) - doesn't require list traversal
* Insertions/deletions other than the first element require traversal, so O(n)

#### Double Linked Lists
Each item in the list references the item before and after itself. Thus, adding and removign from either the front or the end happens in constant time (O(1))
* Tail: final item in the list
* Each node has it's value, a reference to the item before and a reference to the item after
* The head will point back to null just as th tail points forward to null
* To insert at head: make new node and assign old head to the new node's next node. Move the 'before' value from the old head (null) to the new node's 'before' value and make the new node the old node's 'before'. Finally, assign head to the new node. This is all O(1)
* To insert at tail: make new node and assign tail's 'next' value to new node's 'next' value (null for tail). Assign old tail to new node's 'before' value and make old tail's 'next' value equal to the new node. Finally, assign tail to new node. O(1)
* To remove at head: assign node to temp variable and change head to the next node. Set the new head's 'previous' value to the 'previous' value of the removed node (null for head). Return temp. O(1)
* To remove from tail: assign node to temp variable and make 2nd from tail's 'next' node equal to removed node's 'next' field (null for tail); Assign tail to the 2nd from tail node and return temp.
##### To insert node A between nodes B and C:
* Assign A's next value to B's next value
* Assign A's previous value to C's previous value
* Assign B's next field to A
* Assign C's previous field to A
* This is a O(1) operation, but finding the insertion point is a O(n) operation, so actually inserting mid list is O(n)
##### To remove node A from between nodes B and C:
* Assign A to temp variable
* Assign C's previous value to B
* Assign B's next value to A
* return temp variable
* O(1) for operation, but O(n) to find A in the list
