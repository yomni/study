package io.yomni.testcontainer.infrastructure;

import io.yomni.testcontainer.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
