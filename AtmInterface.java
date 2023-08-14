import java.util.*;

class User {
    private String userId;
    private String userPin;
    private double accountBalance;

    public User(String userId, String userPin, double accountBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.accountBalance = accountBalance;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double balance) {
        accountBalance = balance;
    }
}

class Transaction {
    public enum TransactionType { WITHDRAW, DEPOSIT, TRANSFER }

    private TransactionType type;
    private double amount;
    private Date date;

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}

class ATM {
    private List<Transaction> transactionHistory;
    private User currentUser;
        public void withdraw(double amount) {
        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            Transaction transaction = new Transaction(Transaction.TransactionType.WITHDRAW, amount);
            transactionHistory.add(transaction);
            System.out.println("Withdrawal successful. New balance: " + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
            Transaction transaction = new Transaction(Transaction.TransactionType.DEPOSIT, amount);
            transactionHistory.add(transaction);
            System.out.println("Deposit successful. New balance: " + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
            public void transfer(double amount, User recipient) {
        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            recipient.setAccountBalance(recipient.getAccountBalance() + amount);

            Transaction transaction = new Transaction(Transaction.TransactionType.TRANSFER, amount);
            transactionHistory.add(transaction);

            System.out.println("Transfer successful. New balance: " + currentUser.getAccountBalance());
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }

    public ATM(User user) {
        this.currentUser = user;
        this.transactionHistory = new ArrayList<>();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. View Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewTransactionsHistory();
                    break;
                case 2:
                    System.out.println("Enter the amount to withraw:");
                    int amountToWithDraw=scanner.nextInt();
                    withdraw(amountToWithDraw);
                    break;
                case 3:
                    System.out.println("Enter the amount to Deposit:");
                    int amountToDeposit=scanner.nextInt();
                    deposit(amountToDeposit);
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    public void viewTransactionsHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println("Type: " + transaction.getType() +
                               ", Amount: " + transaction.getAmount() +
                               ", Date: " + transaction.getDate());
        }
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        User user = new User("12345", "9876", 1000.0);

        ATM atm = new ATM(user);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userId = scanner.next();
        System.out.print("Enter user PIN: ");
        String userPin = scanner.next();

        if (userId.equals(user.getUserId()) && userPin.equals(user.getUserPin())) {
            System.out.println("Login successful.");
            atm.showMenu();
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }
}
