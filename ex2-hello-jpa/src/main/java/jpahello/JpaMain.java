package jpahello;

import jpahello.domain.Member;
import jpahello.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        tx.begin();
        try {
            /**
             * Member를 조회할 때 Team도 조회해야할까?
             */
            Team team = new Team();
            team.setName("draem team");
            em.persist(team);

            Member member1 = new Member();
            member1.setUserName("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUserName("member1");
            member1.setTeam(team);
            em.persist(member2);

            em.flush();
            em.clear();

            Member member = em.find(Member.class, member1.getId());
            System.out.println("member = " + member.getUserName());
            System.out.println("member = " + member.getTeam());

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
