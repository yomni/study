package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @JsonIgnore
    @OneToOne(fetch = LAZY, mappedBy = "delivery")
    private Order order;
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // [READY, COMP]
}
