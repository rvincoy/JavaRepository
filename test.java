import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Menu Options:");
            System.out.println("  1. Add a Score");  
            System.out.println("  2. Compute Grade"); 
            System.out.println("  3. Save to File");
            System.out.println("  4. Load from File");
            System.out.println("  5. Quit");
            System.out.print("Select a choice from the menu: ");
            choice=scanner.nextInt();
            if (choice==1) {
                System.out.println("You entered 1");
            } else if (choice==5) {
                System.out.println("You entered 5. Quit");
            }         
        } while(choice!=5);
        scanner.close();
    }
}
