package jpahello.domain;

import javax.persistence.*;

@Entity
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID", nullable = false)
    private Long id;
//    @ManyToOne(fetch = FetchType.LAZY) //************
    @ManyToOne(fetch = FetchType.LAZY) //************
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    @Column(name = "USER_NAME")
    private String userName;
//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", team=" + team +
                ", userName='" + userName + '\'' +
                '}';
    }
}
