package Loops;

// Print reverse of a number
import java.util.*;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");

        int n = sc.nextInt();
        int rev = 0;
        while (n != 0) {
            int last_digit = n % 10;
            rev = rev * 10 + last_digit;
            n /= 10;
        }
        System.out.println("Reversed number: " + rev);
    }
}