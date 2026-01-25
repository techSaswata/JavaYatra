package Dynamic_Programming;
import java.util.*;

public class fibonacci {
    public static int fib (int n, int[] f){
        if (n==0 || n==1){ //base case
            return n;
        }
        if (f[n] != 0){ //fib(n) already calculated
            return f[n];
        }
        f[n] = fib(n-1, f) + fib(n-2, f);
        return f[n];
    }
    public static void main(String[] args) {
        int n=5;

        int f[] = new int[n+1]; //array to store fibonacci values, inital val = 0

        System.out.println(fib(n, f));
    }
}