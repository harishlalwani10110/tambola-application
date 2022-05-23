package com.harish.tambola;

import com.harish.tambola.dto.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tambola")
@CrossOrigin
public class TicketController {

    @Autowired
    TicketService ticketService;


    @GetMapping(value = "/ticket/{numberOfTickets}", produces = "application/json")
    public Tickets getTickets(@PathVariable Integer numberOfTickets) {
        return ticketService.getTickets(numberOfTickets);
    }
}
