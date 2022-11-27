package jpashop;

import jpashop.domain.Member;
import jpashop.domain.Team;

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
            Member member1 = new Member("yomni");
            Member member2 = new Member("tess");
            Member member3 = new Member("platon");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            Team team = new Team("이기는팀내팀");
            em.persist(team);
            // 실수 첫번째 : 연관관계 주인이 아닌 녀석이 관리함
//            team.add(member1);
//            team.add(member2);
//            team.add(member3);

            // 첫번째 실수 해결
            member1.setTeam(team);
            member2.setTeam(team);
            member3.setTeam(team);
            tx.commit();

            // 실수 두번째 : 객체 탐색을 자유롭게 하지 못하고 있음
            for (Member member : team.getMembers()) {
                System.out.println("member.getUserName() = " + member.getUserName());
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
