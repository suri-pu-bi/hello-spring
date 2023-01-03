package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 굳이 public으로 하지 않아도 된다.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드가 끝날 때마다 동작
    public void afterEach() {
        repository.clearStore(); // clear 동작
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("sumi");

        repository.save(member);
        Member result = repository.findById(member.getID()).get();
        // Optional이므로 get으로 꺼내기
        System.out.println("result = " + (result == member ));
        // jupiter
        // Assertions.assertEquals(member,result); // (기댓값, 실제값)
        // Assertions.assertEquals(member, null);
        // assertj (요즘)
        // Assertions 없애기
        assertThat(member).isEqualTo((result)); // Assertions 없애기
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("chae");
        repository.save(member1);
        // shift + f6
        Member member2 = new Member();
        member2.setName("jiwon");
        repository.save(member2);

        Member result = repository.findByName("chae").get();
        assertThat(result).isEqualTo(member1);
    }
// 메서드만 돌렸을 때는 에러X, class에서 돌리면 에러O
// 실행되는 순서 알아서 잡아버림 -> Test 하나가 끝나고나면 데이터를 clear 해줘야함.
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("chae");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sumi");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}

// 미리 검증할 수 있는 틀 (test)를 먼저 만들어놓고 구현 클래스 만들기 ->  test 주도 개발 "PDD"
// test가 여러개면 폴더 우클릭후 run 선택
