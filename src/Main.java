import javax.swing.JOptionPane;

public class Main {

    private static final String TITLE = "Bank Simulation";

    public static void main(String[] args) {
        Bank bank = new Bank();

        String menu = "===== BANK SIMULATION =====\n\n"
                + "1 - Create Account\n"
                + "2 - Deposit Money\n"
                + "3 - Withdraw Money\n"
                + "4 - Check Balance\n"
                + "5 - Transfer Money\n"
                + "6 - Show All Accounts\n"
                + "0 - Exit\n\n"
                + "Choose an option:";

        int choice = -1;

        while (choice != 0) {
            String input = JOptionPane.showInputDialog(null, menu, TITLE, JOptionPane.QUESTION_MESSAGE);

            // Cancel or the X button closes the program
            if (input == null) {
                break;
            }

            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                showMessage("Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    createAccount(bank);
                    break;
                case 2:
                    deposit(bank);
                    break;
                case 3:
                    withdraw(bank);
                    break;
                case 4:
                    checkBalance(bank);
                    break;
                case 5:
                    transfer(bank);
                    break;
                case 6:
                    showMessage(bank.showAccounts());
                    break;
                case 0:
                    break;
                default:
                    showMessage("Invalid option. Please try again.");
            }
        }

        showMessage("Goodbye!");
    }

    // ---------- Menu actions ----------
    // If the user presses Cancel in any step, we go back to the menu

    private static void createAccount(Bank bank) {
        String name = readText("Name:");
        if (name == null) return;
        String surname = readText("Surname:");
        if (surname == null) return;
        String accountNumber = readText("Account Number:");
        if (accountNumber == null) return;
        Double initialBalance = readAmount("Initial Balance (TL):");
        if (initialBalance == null) return;

        showMessage(bank.createAccount(name, surname, accountNumber, initialBalance));
    }

    private static void deposit(Bank bank) {
        String accountNumber = readText("Account Number:");
        if (accountNumber == null) return;
        Double amount = readAmount("Amount to deposit (TL):");
        if (amount == null) return;

        showMessage(bank.deposit(accountNumber, amount));
    }

    private static void withdraw(Bank bank) {
        String accountNumber = readText("Account Number:");
        if (accountNumber == null) return;
        Double amount = readAmount("Amount to withdraw (TL):");
        if (amount == null) return;

        showMessage(bank.withdraw(accountNumber, amount));
    }

    private static void checkBalance(Bank bank) {
        String accountNumber = readText("Account Number:");
        if (accountNumber == null) return;

        showMessage(bank.checkBalance(accountNumber));
    }

    private static void transfer(Bank bank) {
        String fromNumber = readText("From Account Number:");
        if (fromNumber == null) return;
        String toNumber = readText("To Account Number:");
        if (toNumber == null) return;
        Double amount = readAmount("Amount to transfer (TL):");
        if (amount == null) return;

        showMessage(bank.transfer(fromNumber, toNumber, amount));
    }

    // ---------- Input helpers ----------

    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    // Asks until the user writes something. Returns null if Cancel is pressed.
    private static String readText(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message, TITLE, JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return null;
            }
            if (!input.trim().isEmpty()) {
                return input.trim();
            }
            showMessage("This field cannot be empty.");
        }
    }

    // Asks until the user writes a valid number. Returns null if Cancel is pressed.
    private static Double readAmount(String message) {
        while (true) {
            String input = readText(message);
            if (input == null) {
                return null;
            }
            try {
                // Accept both 10.5 and 10,5
                double amount = Double.parseDouble(input.replace(',', '.'));
                if (Double.isFinite(amount)) {
                    return amount;
                }
            } catch (NumberFormatException e) {
                // invalid number, ask again below
            }
            showMessage("Please enter a valid amount (e.g. 250 or 250.50).");
        }
    }
}
