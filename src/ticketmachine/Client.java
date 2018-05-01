package ticketmachine;

public class Client {
    /**
     * The total client balance.
     *
     * @var double
     */
    private double balance = 0;

    /**
     * The current administrator status.
     *
     * @var boolean
     */
    private boolean admin = false;

    /**
     * Return the total balance.
     *
     * @return double
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Set the total balance.
     *
     * @param amount
     */
    public void setBalance(double amount) {
        this.balance = amount;
    }

    /**
     * Add amount to the total balance.
     *
     * @param amount
     */
    public void addBalance(double amount) {
        this.balance += amount;
    }

    /**
     * Remove amount from the the total balance.
     *
     * @param amount
     */
    public void removeBalance(double amount) {
        this.balance -= amount;
    }

    /**
     * Check if client can afford amount.
     *
     * @param amount
     * @return boolean
     */
    public boolean canAfford(double amount) {
        return this.balance >= amount;
    }

    /**
     * Return the current administrator status.
     *
     * @return boolean
     */
    public boolean isAdmin() {
        return this.admin;
    }

    /**
     * Set the current administrator status.
     *
     * @param isAdmin
     */
    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    /**
     * Attempt to login as administrator.
     *
     * @param password
     * @param attempt
     * @return boolean
     */
    public boolean login(String password, String attempt) {
        // Return false if attempt is incorrect.
        if (! attempt.equals(password)) {
            return false;
        }

        // Enable admin mode and return.
        this.setAdmin(true);
        return true;
    }

    /**
     * Disable administrator mode.
     */
    public void logout() {
        this.setAdmin(false);
    }

    /**
     * Reset the client to default.
     */
    public void reset() {
        this.setBalance(0);
        this.setAdmin(false);
    }
}
