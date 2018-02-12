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
		return ticketPrice;
	}

	/**
	 * Input money into customer balance.
	 */
	public void inputMoney(int amount) {
		balance = balance + amount;
	}

	/**
	 * Return the customers balance.
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Print ticket if sufficient balance.
	 */
	public void printTicket() {
        // Check sufficient balance.
		if (balance < ticketPrice) {
			System.out.println("Insufficient balance. Input more money.");
		}

        // Output ticket to console.
		System.out.println("##########B##T#########");
		System.out.println("# BlueJ Trafikselskab #");
		System.out.println("#                     #");
		System.out.println("#        Ticket       #");
		System.out.println("#        " + ticketPrice + " kr.       #");
		System.out.println("#                     #");
		System.out.println("##########B##T#########");
		System.out.println("# You have " + (balance-ticketPrice) + " DKK left       #");
		System.out.println("##########B##T#########");
		System.out.println();

        // Increase sold count.
		soldTickets++;
        
        // Decrease balance by price.
		balance -= ticketPrice;
	}
    
    /**
     * Return the change amount to the customer.
     */
	public int returnChange() {
		int amount = balance;
		balance = 0;

		System.out.println("You will receive " + amount + " DKK.");

		return amount;
	}

    /**
     * Toggle admin mode if correct password.
     */
	void adminLogin(String password) {
		if ("1234".equals(password)) {
			adminMode = true;

			System.out.println("Admin mode activated!");
			System.out.println("You may now enter ticket price.");
		} else {
			adminMode = false;

			System.out.println("Admin mode deactivated!");
		}
	}

    /**
     * Return the total amount of money earned.
     */
	public int getTotal() {
		if (adminMode) {
			return ticketPrice * soldTickets;
		} else {
			System.out.println("Rejected - You must login.");
			return 0;
		}
	}

    /**
     * Return the amount of sold tickets.
     */
	public int getSoldTickets() {
		if (adminMode) {
			return soldTickets;
		} else {
			System.out.println("Rejected - You must login.");
			return 0;
		}
	}

    /**
     * Set the price of the ticket.
     */
	public void setTicketPrice(int price) {
		this.ticketPrice = price;
	}

    /**
     * Reset the admin mode login.
     */
	public void reset() {
		if (adminMode) {
			soldTickets = 0;
		} else {
			System.out.println("Rejected - You must login.");
		}
	}

    /**
     * Set the amount of tickets sold.
     */
	public void setSoldTickets(int amount) {
		if (adminMode) {
			this.soldTickets = amount;
		} else {
			System.out.println("Rejected - You must login.");
		}
	}

    /**
     * Return weather in admin mode or not. 
     */
	public boolean isAdmin() {
		return adminMode;
	}
}