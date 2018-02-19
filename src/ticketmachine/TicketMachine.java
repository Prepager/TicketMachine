package ticketmachine;

public class TicketMachine {
    private int ticketPrice = 10;
    private int balance = 0;
    private int soldTickets = 0;
    private boolean adminMode;

    /**
    * Return the ticket price.
    */
    public int getTicketPrice() {
        return this.ticketPrice;
    }

    /**
    * Input money into customer balance.
    */
    public void inputMoney(int amount) {
        System.out.println("Added " + amount + " to the balance.");
        this.balance += amount;
    }

    /**
    * Return the customers balance.
    */
    public int getBalance() {
        return this.balance;
    }

    /**
    * Print ticket if sufficient balance.
    */
    public void printTicket() {
        // Check sufficient balance.
        if (this.balance < this.ticketPrice) {
            System.out.println("Insufficient balance. Input more money.");
            return;
        }

        // Output ticket to console.
        System.out.println("##########B##T#########");
        System.out.println("# BlueJ Trafikselskab #");
        System.out.println("#                     #");
        System.out.println("#        Ticket       #");
        System.out.println("#        " + this.ticketPrice + " kr.       #");
        System.out.println("#                     #");
        System.out.println("##########B##T#########");
        System.out.println("# You have " + (this.balance - this.ticketPrice) + " DKK left       #");
        System.out.println("##########B##T#########");
        System.out.println();

        // Increase sold count.
        System.out.println("Increased ticket sale count.");
        this.soldTickets++;

        // Decrease balance by price.
        System.out.println("Removed ticket price from balance.");
        this.balance -= this.ticketPrice;
    }

    /**
    * Return the change amount to the customer.
    */
    public int returnChange() {
        // Save balance and reset.
        int amount = this.balance;
        this.balance = 0;

        // Output notification.
        System.out.println("You will receive " + amount + " DKK.");

        // Return amount.
        return amount;
    }

    /**
    * Toggle admin mode if correct password.
    */
    public void adminLogin(String password) {
        // Check password.
        if ("1234".equals(password)) {
            // Enable admin mode.
            this.adminMode = true;

            // Output login notification.
            System.out.println("Admin mode activated!");
            System.out.println("You may now enter ticket price.");

            // Return out of function.
            return;
        }

        // Disable admin mode.
        this.adminMode = false;

        // Output logout notification.
        System.out.println("Admin mode deactivated!");
    }

    /**
    * Return the total amount of money earned.
    */
    public int getTotal() {
        // Check admin mode and return earnings.
        if (this.adminMode) {
            return this.ticketPrice * this.soldTickets;
        }

        // Output rejected message and return 0.
        System.out.println("Rejected - You must login.");
        return 0;
    }

    /**
    * Return the amount of sold tickets.
    */
    public int getSoldTickets() {
        // Check admin mode and return ticket sales count.
        if (this.adminMode) {
            return this.soldTickets;
        }

        // Output rejected message and return 0.
        System.out.println("Rejected - You must login.");
        return 0;
    }

    /**
    * Set the price of the ticket.
    */
    public void setTicketPrice(int price) {
        // Check admin mode and set ticket price.
        if (this.adminMode) {
            this.ticketPrice = price;
            return;
        }

        // Output rejected message.
        System.out.println("Rejected - You must login.");
    }

    /**
    * Reset the admin mode login.
    */
    public void reset() {
        // Check admin mode.
        if (this.adminMode) {
            // Reset sold ticket count.
            System.out.println("Reset sold ticket count.");
            this.soldTickets = 0;
            return;
        }

        // Output rejected message.
        System.out.println("Rejected - You must login.");
    }

    /**
    * Set the amount of tickets sold.
    */
    public void setSoldTickets(int amount) {
        // Check admin mode.
        if (this.adminMode) {
            // Set sold ticket count.
            System.out.println("Set sold ticket count to " + amount);
            this.soldTickets = amount;
            return;
        }

        // Output rejected message.
        System.out.println("Rejected - You must login.");
    }

    /**
    * Return weather in admin mode or not. 
    */
    public boolean isAdmin() {
        return this.adminMode;
    }
}
