import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // This will create a Scanner to get user input something from the keyboard
        StringBuilder allExpenses = new StringBuilder(); // StringBuilder stores all the expenses entries as a text in word.txt file
        double total = 0;

        System.out.println("Welcome to Your Daily Expenses Tracker!");

        while (true) {
            System.out.print("Enter Name of Item You Purchase (Or Enter 'exit' to Finish): "); // it will start a loop to get the expenses from the user
            String item = scanner.nextLine();

            // if the user input the exit then the following code will make the loop stop
            if (item.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter the amount you spent on each purchase " + item + ": ");
            String amountText = scanner.nextLine();

            try {
                double amount = Double.parseDouble(amountText);
                total += amount;
                allExpenses.append(item).append(" - $").append(amount).append("\n");
            } catch (Exception e) {
                System.out.println("Invalid amount entered. Please Try again !"); // this will handle error if number is not input valid
            }
        }

        allExpenses.append("Total amount you spent today is : $").append(total).append("\n");

        // this will store information in word.txt file
        try (FileWriter writer = new FileWriter("word.txt")) {
            writer.write(allExpenses.toString());
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }

        System.out.println("Total amount of money you spent on whole purchase is: $" + total); // this will show total amount you spent on console
        scanner.close();
    }
}
