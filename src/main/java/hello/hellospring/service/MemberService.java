package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// service ->  비즈니스 로직 -> 비즈니스 용어
/* MemoryMemberRepository라는 class는 interface인 MemberRepository를 구현하고, 그 구현한 interface  MemberService
* */
public class MemberService {
// 익명 클래스: 클래스의 선언과 객체의 생성을 동시에 하기 때문에 단 한번만 사용될 수 있고, 오직 하나의 객체만을 생성할 수 있는 일회용 클래스
// private final class AnoymousMemberRepository implements MemoryMemberRepository {안에 함수들}
// private final MemroyMemberRepository memberRepository = new AnonymousMemberRepository();
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 익명 클래스

    /* 회원 가입 */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // result.getorElseGet() : 값이 있으면 꺼내고, 없으면 어떤 메소드를 수행해
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // Optional 리턴값 타입 불편
        // 메소드로 뽑기 맥: ctrl + opt +m , 윈도우 : ctrl + alt + m
        vaildateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getID();
    }

    private void vaildateDuplicateMember (Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }
    /* 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll(); // 반환 타입 List이므로 바로 반환 가능
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById((memberId));
    }
}
