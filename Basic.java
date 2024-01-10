import java.util.Scanner;

public class Basic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        checknumber(scanner);

        scanner.close();
        // printHello();
        // add();
    }

    public static void printHello() {
        System.out.println("Hello world");
    }

    public static void add() {
        int a = 10;
        int b = 5;
        int c = 15;

        System.out.println("Sum: " + (a + b + c));
        System.out.println("Difference: " + (a - b - c));
        System.out.println("Bitwise AND: " + (a & b & c));

        String str1 = "Java";
        String str2 = "Programming";
        String concatenatedString = concatenateStrings(str1, str2);
        System.out.println("Concatenated String: " + concatenatedString);
    }

    public static String concatenateStrings(String str1, String str2) {
        return str1 + " " + str2;
    }

    public static void checknumber(Scanner scanner) {
        System.out.println("Enter a numeric value for D: ");
        int number = scanner.nextInt();

        if (number > 0) {
            System.out.println("The number is positive.");
        } else if (number < 0) {
            System.out.println("The number is negative.");
        }
    }
}

