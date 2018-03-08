package ticketmachine.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ticketmachine.TicketMachine;
import ticketmachine.Ticket;

public class ContentFrame extends Frame {
    /**
     * The ticket machine for the frame.
     *
     * @var TicketMachine
     */
    protected TicketMachine machine;
    
    /**
     * Make program close on exit.
     */
    public ContentFrame() {
        // Set closing behaviour to exit on close.
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Bind actions.
        this.bindActions();
    }

    /**
     * Refresh the content on the frame.
     */
    public void refresh() {
        // Update the total balance.
        this.setBalance(this.machine.getClient().getBalance());
        
        // Remove all tickets from dropdown.
        this.Tickets.removeAllItems();
        
        // Add ticket types to both user and admin dropdowns.
        this.Tickets.setModel(
            new DefaultComboBoxModel(
                this.machine.getTickets().toArray()
            )
        );

        this.SoldTickets.setModel(
            new DefaultComboBoxModel(
                this.machine.getTickets().toArray()
            )
        );
        
        // Only update admin data if logged in.
        if (this.machine.getClient().isAdmin()) {
            // Update the total amount sold.
            this.Total.setText("Total: " + String.valueOf(this.machine.getTotal()) + " DKK");
        }
    }

    /**
     * Bind the machine to the frame.
     *
     * @param newMachine
     */
    public void setMachine(TicketMachine newMachine) {
        this.machine = newMachine;
    }

    /**
     * Update balance text and set return status.
     *
     * @param amount 
     */
    public void setBalance(double amount) {
        // Update the balance text.
        this.Balance.setText("Balance: " + String.valueOf(amount) + " DKK");

        // Update the return change enabled status.
        this.ReturnChange.setEnabled(amount > 0);
    }
    
    /**
     * Bind the various buttons to events.
     */
    public final void bindActions() {
        // Refresh data on tab change.
        this.Tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ContentFrame.this.refresh();
            }
        });
        
        // Bind return change button.
        this.ReturnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContentFrame.this.machine.returnChange();
                ContentFrame.this.refresh();
            }
        });

        // Bind input amount button.
        this.InputAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(ContentFrame.this.Amount.getText());

                ContentFrame.this.machine.inputMoney(amount);
                ContentFrame.this.refresh();
                
                ContentFrame.this.Amount.setText("0");
            }
        });
        
        // Bind purchase ticket button.
        this.PurchaseTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket ticket = (Ticket) ContentFrame.this.Tickets.getSelectedItem();

                ContentFrame.this.machine.purchaseTicket(ticket.getID());
                ContentFrame.this.refresh();
            }
        });
        
        // Hide protected pane on startup.
        this.Protected.setVisible(false);
        
        // Bind login button.
        this.Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get password from element.
                String password = String.valueOf(ContentFrame.this.Password.getPassword());
                
                // Attempt to login.
                boolean attempt = ContentFrame.this.machine.login(password);

                // If successful show protected pane.
                if (attempt) {
                    ContentFrame.this.LoginPane.setVisible(false);
                    ContentFrame.this.Protected.setVisible(true);
                }
                
                // Reset password field to 'secret'.
                ContentFrame.this.Password.setText("secret");
                
                // Refresh the data with protected.
                ContentFrame.this.refresh();
            }
        });
        
        // Bind logout button.
        this.Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset admin state by loggin in empty.
                ContentFrame.this.machine.logout();
                
                // Hide protected pane and show login.
                ContentFrame.this.LoginPane.setVisible(true);
                ContentFrame.this.Protected.setVisible(false);
            }
        });
        
        // Bind sold tickets dropdown.
        this.SoldTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket ticket = (Ticket) ContentFrame.this.SoldTickets.getSelectedItem();
                
                ContentFrame.this.Sold.setText("Sold: " + ticket.getSold());
            }
        });
    }
}
