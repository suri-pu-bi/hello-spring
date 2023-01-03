package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 클래스 상속과 다르게 인터페이스 상속은 implements 사용
public class MemoryMemberRepository implements MemberRepository { // alt+enter 상속받은 method 불러오기
    // map: 인터페이스
    // hashmap: map 인터페이스를 구현한 구현체
    // Map<Long, Member> store: map interface를 구현한 모든 객체를 담을 수 있다는 의미
    // = new HashMap<>(); : map의 구현체인 HashMap을 생성해서 할당하겠다는 의미
    // HashMap<Long, Member> store = new HashMap<>(); 이것도 사용할 수 있으나
    // 자바의 다형성떄문에 Map으로 선언, map 인터페이스로 선언하면 여러가지 map들을 사용할 수 있음.
    // 즉, map이라는 공통된 인터페이스로 개발하면, 바꾸는 것도 간편하고, 커스터마이징도 간편
    // ArrayList로 하면, List의 한 구현체에 불과 / 제품목록을 가져오는데 배열을 백엔드로 하는 구현체에 담게 되버림.
    // List 인터페이스를 정의하면 자유도 상승, 의도 명확하게 한정 ...
    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 hashmap 동시성 문제 존재 -> concurrentmap 써야함

    // long 변수는 데이터를 초기화할때 뒤에 L를 붙여줘야함 , float은 F (변수에 데이터 저장하기전 임시로 메모리에 저장할 때 기본 데이터 타입이 int이기 때문)
    // 실무에서는 동시성 문제 존재 -> AtomicLong 써야함
    private static long sequence = 0L; // sequence 변수 하나씩 증가시켜서 0, 1, 2 ... 키 값을 생성하도록 함.

    // @override annotation : 주석, 컴파일러에게 특정한 정보 제공 / 재정의된 메서드라는 정보를 제공
    // 오버로딩(overloading): 기존에 없는 새로운 메서드를 정의하는 것
    // 오버라이딩(overriding): 상속받은 메서드의 내용을 변경하는 것
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // Id를 셋팅
        store.put(member.getID(), member); // HashMap store에 <id, member>로 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Null이여도 optional으로 감싸서 보낼 수 있도록 함
    }

    // stream: 데이터 소스가 무엇이던 간에 같은 방식으로 다룰 수 있고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해놓음
    // 스트림은 iterator로 컬렉션 요소를 다 읽고 나면 (ex)출력 하면) 다시 사용불가
    // .stream() : 스트림을 생성
    // stream.forEach(); : 내부 반복(메서드 안에 for문)
    // .findAny(): stream을 처리할 때 가장 먼저 찾은 요소 리턴
    // .findFirst(): 조건에 일치하는 요소들 중에 Stream 에서 순서가 가장 앞에 있는 요소를 리턴


    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store hashmap의 member를 반환 <- stream으로 생성해서 람다식에 맞는 게 있으면 반환
                .filter(member -> member.getName().equals(name)) // 인자로 받은 name과 member의 name이랑 같은 게 있으면 (lambda 사용)
                .findAny(); // 끝까지 돌았는데 없으면 optional에 null 감싸서 반환
    }

    // 배열 int [] score = ne int[5]; (배열의 길이 미리 지정)

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store의 멤버들 모두 반환
    }

    public void clearStore() {
        store.clear();
    }
}

// 검증은 어떻게 할까? test case 작성하기!