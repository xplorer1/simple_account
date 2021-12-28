import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

class Transactions {
    LocalDateTime date;
    double amount;
    String type;

    public Transactions(LocalDateTime date, double amount, String type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }
}

public class AccountActions {
    double balance;
    String customerName;
    int accountId;
    ArrayList<Transactions> transactions = new ArrayList<>();

    public AccountActions(String cName, int aId) {
        this.accountId = aId;
        this.customerName = cName;
    }

    void displayTxns(LocalDateTime date, double amount, String type) {
        System.out.println("==============================");
        System.out.println("Transaction date: " + date);

        System.out.println("Transaction amount: " + amount);

        System.out.println("Transaction type: " + type);
        System.out.println("===============================");
    }

    void makeDeposit(double amount) {
        if(amount != 0) {
            this.balance += amount;
            LocalDateTime newDate = LocalDateTime.now();
            this.transactions.add(new Transactions(newDate, amount, "credit"));
        }
    }

    void makeWithdrawal(double amount) {
        if(amount != 0 ) {
            this.balance -= amount;

            LocalDateTime newDate = LocalDateTime.now();
            this.transactions.add(new Transactions(newDate, amount, "debit"));
        }
    }

    void calculateInterest(int years) {
        double interestRate = .0185;
        double newBalance = (this.balance * interestRate * years) + this.balance;
        System.out.println("The current interest rate is " + (100 * interestRate) + "%");
        System.out.println("After " + years + " years, you balance will be: " + newBalance);
    }

    void showMenu () {
        char option;

        System.out.println("Hello " + this.customerName + " - " + this.accountId);
        System.out.println();
        System.out.println("Welcome to Standard Bank!");
        System.out.println();
        System.out.println("What would you like to do today?");
        System.out.println();
        System.out.println("A. Check your balance.");
        System.out.println("B. Make a deposit.");
        System.out.println("C. Make a withdrawal.");
        System.out.println("D. View previous transactions.");
        System.out.println("E. Calculate interest.");
        System.out.println("F. Exit.");

        do {
            System.out.println();
            System.out.println("Enter an option.");

            Scanner optionScanner = new Scanner(System.in);
            option = optionScanner.next().charAt(0);
            option = Character.toUpperCase(option);

            System.out.println();

            switch (option) {
                case 'A':
                    System.out.println("===============================");
                    System.out.println();
                    System.out.println("Your balance is £" + this.balance);
                    System.out.println();
                    System.out.println("================================");

                    break;
                case 'B':
                    System.out.println("===============================");
                    System.out.println("Enter amount to deposit:");

                    double deposityAmount = optionScanner.nextDouble();
                    this.makeDeposit(deposityAmount);

                    System.out.println("Your balance is now " + this.balance);
                    System.out.println("================================");

                    break;

                case 'C':
                    System.out.println("===============================");
                    System.out.println("Enter amount to withdraw:");

                    double withdrawAmount = optionScanner.nextDouble();
                    this.makeWithdrawal(withdrawAmount);

                    System.out.println("Your balance is now " + this.balance);
                    System.out.println("================================");

                    break;
                case 'D':

                    for(Transactions txn : transactions) {
                        displayTxns(txn.date, txn.amount, txn.type);
                        System.out.println();
                    };

                    break;

                case 'E':
                    System.out.println("===============================");
                    System.out.println("Enter number of years to calculate:");

                    int interestYears = optionScanner.nextInt();
                    this.calculateInterest(interestYears);

                    break;
                case 'F':
                    System.out.println("====================================");
                    System.out.println("Thank you for banking with us.");
                    System.out.println("====================================");
                    break;
                default:
                    System.out.println("Error: invalid option. Please enter A, B, C, D, or E or F to exit.");
                    break;
            }
        } while (option != 'F');
    }
}
