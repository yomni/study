package jpashop.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member() {
    }

    public Member(String userName) {
        this.userName = userName;
    }

    public Member(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Team getTeam() {
        return team;
    }

    /**
     * 일종의 연관관계 편의 메서드
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
        // 두번째 실수 해결
        team.add(this);
    }
}