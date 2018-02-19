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
        System.out.println("Reset balance.");
        int amount = this.balance;
        this.balance = 0;

        System.out.println("You will receive " + amount + " DKK.");

        return amount;
    }

    /**
    * Toggle admin mode if correct password.
    */
    void adminLogin(String password) {
        if ("1234".equals(password)) {
            this.adminMode = true;

            System.out.println("Admin mode activated!");
            System.out.println("You may now enter ticket price.");
        } else {
            this.adminMode = false;

            System.out.println("Admin mode deactivated!");
        }
    }

    /**
    * Return the total amount of money earned.
    */
    public int getTotal() {
        if (this.adminMode) {
            return this.ticketPrice * this.soldTickets;
        } else {
            System.out.println("Rejected - You must login.");
            return 0;
        }
    }

    /**
    * Return the amount of sold tickets.
    */
    public int getSoldTickets() {
        if (this.adminMode) {
            return this.soldTickets;
        } else {
            System.out.println("Rejected - You must login.");
            return 0;
        }
    }

    /**
    * Set the price of the ticket.
    */
    public void setTicketPrice(int price) {
        System.out.println("Set the ticket price to " + price);
        this.ticketPrice = price;
    }

    /**
    * Reset the admin mode login.
    */
    public void reset() {
        if (this.adminMode) {
            System.out.println("Reset sold ticket count.");
            this.soldTickets = 0;
        } else {
            System.out.println("Rejected - You must login.");
        }
    }

    /**
    * Set the amount of tickets sold.
    */
    public void setSoldTickets(int amount) {
        if (this.adminMode) {
            System.out.println("Set sold ticket count to " + amount);
            this.soldTickets = amount;
        } else {
            System.out.println("Rejected - You must login.");
        }
    }

    /**
    * Return weather in admin mode or not. 
    */
    public boolean isAdmin() {
        return this.adminMode;
    }
}
