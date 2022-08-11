package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    // 직접 MemberService 내에서 직접 MemoryMemberRepository를 생성하지 않고,
    // MemberService를 만들 때 외부에서 넣어주는 것 == 의존성 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository  = memberRepository;
    }
    /**
     * 회원 가입
     *  - 같은 이름을 가진 회원 존재 불가
     * */
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
