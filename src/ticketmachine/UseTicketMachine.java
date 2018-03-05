package ticketmachine;

import ticketmachine.tickets.Ticket;

public class UseTicketMachine
{
	public static void main(String[] arg)
	{
		TicketMachine machine = new TicketMachine();
		java.util.Scanner keyboard = new java.util.Scanner(System.in);
        
        machine.addTicket(new Ticket(3, 25, "x"));
        machine.addTicket(new Ticket(1, 50, "y"));
        machine.addTicket(new Ticket(2, 100, "z"));

		System.out.println("UseTicketMachine V3");
		System.out.println();

		while (true) {
			System.out.println("-----------------------------------------------");
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
				System.out.println("Click 13 to logout of admin mode. (admin)");
				System.out.println("Click 14 to print transaction log. (admin)");
			}

			int selection = keyboard.nextInt();
			keyboard.nextLine();

            switch (selection) {
                case 1:
                    System.out.print("Input amount: ");
                    int beløb = keyboard.nextInt();
                    machine.addMoney(beløb);
                    break;
                case 2:
                    machine.listTickets();
                    int ticket = keyboard.nextInt();
                    machine.printTicket(ticket);
                    break;
                case 3:
                    machine.returnChange();
                    break;
                case 10:
                    System.out.print("Input password: ");
                    String kode = keyboard.next();
                    machine.adminLogin(kode);
                    break;
                case 11:
                    System.out.println("Sold tickets: " + machine.getSoldTickets());
                    System.out.println("Total income: " + machine.getTotal()+" dKK");
                    break;
                case 12:
                    machine.reset();
                    break;
                case 13:
                    machine.adminLogin("");
                    break;
                case 14:
                    machine.printLog();
                    break;
                default:
                    System.out.println("Invalid selection, try again.");
                    break;
            }
		}
	}
}