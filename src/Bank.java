import java.util.ArrayList;

// Every method returns a message, so Main can show it in a window
public class Bank {

    private ArrayList<Account> accounts = new ArrayList<>();

    public String createAccount(String name, String surname, String accountNumber, double initialBalance) {
        if (findAccount(accountNumber) != null) {
            return "This account number already exists.";
        }
        if (initialBalance < 0) {
            return "Initial balance cannot be negative.";
        }

        Account account = new Account(name, surname, accountNumber, initialBalance);
        accounts.add(account);
        return "Account created successfully.\n\n" + account.getInfo();
    }

    // Returns the account with this number, or null if it does not exist
    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);

        if (account == null) {
            return "Account not found.";
        }
        if (amount <= 0) {
            return "Amount must be greater than 0.";
        }

        account.deposit(amount);
        return "Deposit successful.\nNew balance: " + Account.formatMoney(account.getBalance());
    }

    public String withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);

        if (account == null) {
            return "Account not found.";
        }
        if (amount <= 0) {
            return "Amount must be greater than 0.";
        }
        if (amount > account.getBalance()) {
            return "Insufficient balance.\nCurrent balance: " + Account.formatMoney(account.getBalance());
        }

        account.withdraw(amount);
        return "Withdraw successful.\nNew balance: " + Account.formatMoney(account.getBalance());
    }

    public String checkBalance(String accountNumber) {
        Account account = findAccount(accountNumber);

        if (account == null) {
            return "Account not found.";
        }
        return "Account: " + account.getAccountNumber() + "\n"
                + "Balance: " + Account.formatMoney(account.getBalance());
    }

    public String transfer(String fromNumber, String toNumber, double amount) {
        Account sender = findAccount(fromNumber);
        Account receiver = findAccount(toNumber);

        if (sender == null) {
            return "Sender account not found.";
        }
        if (receiver == null) {
            return "Receiver account not found.";
        }
        if (fromNumber.equals(toNumber)) {
            return "You cannot transfer to the same account.";
        }
        if (amount <= 0) {
            return "Amount must be greater than 0.";
        }
        if (amount > sender.getBalance()) {
            return "Insufficient balance.\nSender balance: " + Account.formatMoney(sender.getBalance());
        }

        // All checks passed, so both steps are safe
        sender.withdraw(amount);
        receiver.deposit(amount);
        return "Transfer successful.\n\n"
                + "Sender new balance: " + Account.formatMoney(sender.getBalance()) + "\n"
                + "Receiver new balance: " + Account.formatMoney(receiver.getBalance());
    }

    public String showAccounts() {
        if (accounts.isEmpty()) {
            return "There are no accounts yet.";
        }

        String result = "===== ACCOUNTS =====\n";
        for (Account account : accounts) {
            result = result + "\n" + account.getInfo() + "\n";
        }
        return result;
    }
}
