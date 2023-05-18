package io.yomni.chapter1.티켓판매.service;

import io.yomni.chapter1.티켓판매.domain.*;

/**
 * Problem : 너무 많은 클래스에 의존하고 있다.
 */
public class Theater {
    private TicketSeller ticketSeller;

    public void enter(Audience audience) {
        /**
         * TODO : 문제를 찾아 정의하고 해결하기
         *   무엇이 문제? --> 로버트 마틴 - 클린 소프트웨어
         *     1. 실행 중에 제대로 동작하는 지 확인
         *     2. 변경을 위해 존재하는 것
         *     3. 간단한 작업만으로 변경이 가능해야 한다는 것
         */

        // 소극장은 관람객의 가방을 열어 초대장이 있는 지 확인한다.
        if (audience.getBag().hasInvitation()) { // 가방안에 초대장이 들어 있으면?
            Ticket ticket = ticketSeller.getTicketOffice().getTicket(); // 판매원은 매표소에 보관돼 있는 티켓을
            audience.getBag().setTicket(ticket); // 관람객의 가방 안으로 옮긴다.
        } else { // 초대장이 없으면?
            Ticket ticket = ticketSeller.getTicketOffice().getTicket(); // 관람객의 가방에서 티켓 금액만큼의 현금을 꺼내
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee()); // 매표소에 적립한 후에 매표소에 보관돼 있는 티켓을 관람객의 가방에 넣어준다.
            audience.getBag().setTicket(ticket);
        }
        // ==> 미친거 아니야?
    }
}
