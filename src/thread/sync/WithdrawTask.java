package thread.sync;

public class WithdrawTask implements Runnable {

    private BankAccount account;
    private int amount;

    public WithdrawTask(BankAccount bankAccount, int amount) {
        this.account = bankAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.withdraw(amount);
    }
}
