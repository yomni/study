package io.yomni.testcontainer.presentation;

import io.yomni.testcontainer.domain.Member;
import io.yomni.testcontainer.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
	private final MemberRepository memberRepository;

	@GetMapping("/members")
	public List<Member> members() {
		return memberRepository.findAll();
	}
}
