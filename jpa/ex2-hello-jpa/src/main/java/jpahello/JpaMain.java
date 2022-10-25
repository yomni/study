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
            Member member = new Member();
            member.setUserName("욤니");
            Team team = new Team();
            team.setName("드림팀");
            member.setTeam(team);
            em.persist(member);
            em.persist(team);
            em.flush();
            em.clear();

            member = em.find(Member.class, 1l);

            printMemberAndTeam(member);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMemberAndTeam(Member member) {
        String userName = member.getUserName();
        System.out.println("userName = " + userName);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());

    }

}
