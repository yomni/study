package jpabook.jpashop.repository.order.simplequery;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderSimpleQueryRepository {
    private final EntityManager em;

    public OrderSimpleQueryRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * 의존성이 다 깨져있음을 인지한 상태에서 사용해야함
     * 대부분 성능은 join 에서 발생(index를 안탄다던지...)
     *
     * 조회용 repository / dto 를 별도 위치시킨다
     *
     * 1. 엔티티를 DTO로 변환하는 방법을 선택한다.
     * 2. 필요하면 페치 조인으로 성능을 최적화 --> 대부분 성능 이슈는 여기서 해결
     * 3. 그래도 안되면 DTO로 직접 조회하는 방법을 사용한다
     * 4. 최후의 방법은 JPA가 제공하는 네이티브 SQL 이나 스프링 JDBC Template을 사용해서 SQL을 직접 사용한다.
     */
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                        "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
