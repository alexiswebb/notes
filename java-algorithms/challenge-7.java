// Challenge: use stack AND queue to check for palindrome
import java.util.LinkedList;
import java.util.ListIterator;

public class Challenge7 {
    public static void main(String[] args) {
        // should return true
        System.out.println(checkForPalindrome("abccba"));

        // should return true
        System.out.println(checkForPalindrome("Was it a car or a cat I saw?"));

        // should return true
        System.out.println(checkForPalindrome("I did, did I?"));
 
        // should return false
        System.out.println(checkForPalindrome("hello"));

        // should return true
        System.out.println(checkForPalindrome("Don't nod"));
    }

    public static boolean checkForPalindrome(String string) {
        LinkedList<Character> stack = new LinkedList<Character>();
        LinkedList<Character> queue = new LinkedList<Character>();
        String cleanString = string.replaceAll("[^a-zA-Z]", "").toLowerCase();

        for (int i = 0; i < cleanString.length(); i++) {
            stack.push(cleanString.charAt(i));
            queue.add(cleanString.charAt(i));
        }
        printList(stack);

        for (int i = 0; i < cleanString.length(); i++) {
            if (stack.pop() != queue.remove()) { return false; }
        }
        return true;
    }

    public static void printList(LinkedList stack) {
        ListIterator<Character> iterator = stack.listIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();
    }
}
