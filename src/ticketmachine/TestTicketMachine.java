package ticketmachine;

public class TestTicketMachine
{
    public static void main(String[] arg)
    {
        TicketMachine machine = new TicketMachine();

        int fails = 0;
        double change;

        change = machine.getTicketPrice();
        if (change != 10) {
            System.out.println("Fail, getTicketPrice() != 10");
        }

        System.out.println("*** Test sales of tickets.");
        machine.addMoney(10);
        machine.printTicket();
        change = machine.returnChange();
        if (change != 0) {
            System.out.println("Fail, returnChange() should return 0, but returned: "+change);
            fails = fails + 1;
        }

        System.out.println("*** Test return change.");
        machine.addMoney(100);
        machine.printTicket();
        change = machine.returnChange();
        if (change != 90) {
            System.out.println("Fail, returnChange() should return 90, but returned: "+change);
            fails = fails + 1;
        }

        System.out.println("*** Test that a user may not use admin mode.");
        change = machine.getSoldTickets();
        if (change != 0) {
            System.out.println("Fail, getSoldTickets() should return 0, but returned: "+change);
            fails = fails + 1;
        }

        System.out.println("*** Test that only the correct admin password works.");
        machine.adminLogin("6789");	
        change = machine.getSoldTickets();
        if (change != 0) {
            System.out.println("Fail, getSoldTickets() should return 0, but returned: "+change);
            fails = fails + 1;
        }

        System.out.println("*** Test that an admin can see amount of sold tickets.");
        machine.adminLogin("1234");
        change = machine.getSoldTickets();
        if (change != 2) {
            System.out.println("Fail, getSoldTickets() should return 2, but returned: "+change);
            fails = fails + 1;
        }

        System.out.println("*** Test other ticket prices.");
        machine.setTicketPrice(20);
        machine.addMoney(100);
        machine.printTicket();
        change = machine.returnChange();
        if (change != 80) {
            System.out.println("Fail, returnChange() should return 80, but returned: "+change);
            fails = fails + 1;
        }
        change = machine.getSoldTickets();
        if (change != 3) {
            System.out.println("Fail, getSoldTickets() should return 3, but returned: "+change);
            fails = fails + 1;
        }

        System.out.println();
        System.out.println();
        System.out.println("There was found "+fails+" errors in the machine.");

        if (fails == 0) {
            System.out.println("The machine was free of errors.");
        } else {
            System.out.println("The machine had errors. Fix now.");			
        }
    }
}