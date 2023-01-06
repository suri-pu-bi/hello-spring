package hello.hellospring.service;

import hello.hellospring.domain.Member;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MemberServiceTest {
    // 맥 ctrl + r : 이전에 실행했던 것을 그대로 실행
    // window shift + f10
//    MemberService memberService = new MemberService();
    // 저장소를 clear 해주기 위해 선언
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // 근데 계속 다른 저장소객체 만들어서 생성됨... 기존 저장소를 사용하고 싶은데.. ?
    // static이 없어지면 다른 DB가 되어버림

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 test를 실행하기전에 넣어주기
    public void beforeeach() {
        memberRepository = new MemoryMemberRepository(); // 메모리 리포지토리를 만들고,
        memberService = new MemberService(memberRepository); // 그 메모리 리포지토리를 회원서비스 클래스의 매개변수로 넣어주면, 같은 DB 사용하게됨
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() { // test코드는 한글로 적어도 됨
        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long saveId = memberService.join(member);
        // then
        // 저장된 게 리포지토리에 있는지
        Member findMember = memberService.findOne(saveId).get(); // optional이므로 꺼내올 땐 get()
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        // shift + f6
        Member member2 = new Member();
        member2.setName("spring");

        // when
        // 예외가 터짐
        memberService.join(member1);
        // 예외 try catch 문
//        try {
//            memberService.join(member2);
//            Assertions.fail("오류가 발생해야 합니다.");
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));
//        }
        // 예외 assertThrows
        // 타입이 사용 가능하지만 인스턴스가 없는 경우 .class유형의 이름을 추가하여 이름을 얻을 수 있음.
        // ctrl + alt + v : 리턴값 자동
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 람다
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findOne() {
    }
}