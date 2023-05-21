package io.yomni.chapter1.티켓판매.service;

import io.yomni.chapter1.티켓판매.domain.Audience;
import io.yomni.chapter1.티켓판매.domain.TicketSeller;

public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        ticketSeller.sellTo(audience);
    }
}
