package ticketmachine;

public class UseTicketMachine
{
	public static void main(String[] arg)
	{
		TicketMachine machine = new TicketMachine();
		java.util.Scanner keyboard = new java.util.Scanner(System.in);

		System.out.println("UseTicketMachine V3");
		System.out.println();

		while (true) {
			System.out.println("-----------------------------------------------");
			System.out.println("Ticket Price: " + machine.getTicketPrice() + " DKK");
			System.out.println("Balance: " + machine.getBalance() + " DKK");
			System.out.println();
			System.out.println("Click 1 to input money.");
			System.out.println("Click 2 to print ticket.");
			System.out.println("Click 3 to recieve change.");
			System.out.println();
			System.out.println("Click 10 to login as admin.");

			if (machine.isAdmin()) {
				System.out.println("Click 11 to see status. (admin)");
				System.out.println("Click 12 to reset machine. (admin)");
				System.out.println("Click 13 to set ticket price. (admin)");
				System.out.println("Click 14 to logout of admin mode. (admin)");
			}

			int selection = keyboard.nextInt();
			keyboard.nextLine();

			if (selection == 1) {
				System.out.print("Input amount: ");
				int beløb = keyboard.nextInt();
				machine.inputMoney(beløb);
			} else if (selection == 2) {
				machine.printTicket();
			} else if (selection == 3) {
				int beløb = machine.returnChange();
				System.out.println("You received "+beløb+" DKK change.");
			} else if (selection == 10) {
				System.out.print("Input password: ");
				String kode = keyboard.next();
				machine.adminLogin(kode);
			} else if (selection == 11) {
				System.out.println("Sold tickets: " + machine.getSoldTickets());
				System.out.println("Total income: " + machine.getTotal()+" dKK");
			} else if (selection == 12) {
				machine.reset();
			} else if (selection == 13) {
				System.out.print("Input amount:: ");
				int beløb = keyboard.nextInt();
				machine.setTicketPrice(beløb);
			} else if (selection==14) {
				machine.adminLogin("");
			} else {
				System.out.println("Invalid selection, try again.");
			}
		}
	}
}