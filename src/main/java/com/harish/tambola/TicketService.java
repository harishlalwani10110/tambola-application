package com.harish.tambola;

import com.harish.tambola.dto.Tickets;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TicketService {

    public Tickets getTickets(Integer numberOfTickets) {
        Tickets tickets = new Tickets();
        tickets.setTicketList(new ArrayList<>());
        return getGeneratedTickets(numberOfTickets, tickets);

    }

    private Tickets getGeneratedTickets(Integer numberOfTickets, Tickets tickets) {
        for (int i = 0; i < numberOfTickets; i++) {
            tickets.getTicketList().add(new TicketGenerator().getTicket());
        }
        return tickets;
    }
}
