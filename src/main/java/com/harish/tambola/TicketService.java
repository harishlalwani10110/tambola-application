package com.harish.tambola;

import com.harish.tambola.dto.Ticket;
import com.harish.tambola.dto.Tickets;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    public Tickets getTickets(Integer numberOfTickets) {
        Tickets tickets = new Tickets();
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            Ticket ticket = new Ticket();
            ticket.setTicketData(TicketGenerator.getTicket());
            ticketList.add(ticket);
        }
        tickets.setTicketList(ticketList);
        return tickets;
    }
}
