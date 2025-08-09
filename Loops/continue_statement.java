package Loops;

// Skip num if num div by 10
import java.util.Scanner;

public class continue_statement {
    public static void main(String[] args) {

        Scanner sc = new Scanner (System.in);    
        while (true){
            System.out.print("Enter your Number: ");
            int n = sc.nextInt();
            if (n%10==0){
                System.out.println("Skipping");
                continue;
            } else{
                System.out.println(n);
            }
        } 
    }
}
