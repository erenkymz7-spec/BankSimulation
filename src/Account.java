public class Account {

    private String name;
    private String surname;
    private String accountNumber;
    private double balance;

    public Account(String name, String surname, String accountNumber, double balance) {
        this.name = name;
        this.surname = surname;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Balance cannot be set from outside; it only changes with deposit() and withdraw()

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance = balance + amount;
        return true;
    }

    // Returns false if the amount is invalid or there is not enough money
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance = balance - amount;
        return true;
    }

    public String getInfo() {
        return "Account Number: " + accountNumber + "\n"
                + "Name: " + name + " " + surname + "\n"
                + "Balance: " + formatMoney(balance);
    }

    public static String formatMoney(double amount) {
        return String.format("%.2f TL", amount);
    }
}
