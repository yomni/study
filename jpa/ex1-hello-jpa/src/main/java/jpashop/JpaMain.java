package jpashop;

import jpashop.domain.Member;
import jpashop.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member1 = new Member(null, "전용민1");
            Member member2 = new Member(null, "전용민2");
            Member member3 = new Member(null, "전용민3");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            List<Member> list = new ArrayList<>();
            Team team = new Team(null, "이기는팀내팀", list);
            em.persist(team);
            team.add(member1);
            team.add(member2);
            team.add(member3);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
