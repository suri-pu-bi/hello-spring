package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 추상클래스: 상속을 통해서 자식클래스에 의해서만 완성될 수 있다. 추상메서드를 포함한다.
// abstract class 클래스이름 {}
// 추상메서드: 메서드는 선언부와 구현부로 구성되어 있는데, 선언부만 작성하고 구현부는 작성하지 않은 채로 남겨둔 것
// abstract 리턴타입 메서드이름();
// interface : 구현된 것은 아무 것도 없고 밑그림만 그려져있는 기본 설계도
// 인터페이스도 자신에 정의된 추상메서드의 몸통을 만들어주는 클래스를 작성해야함 -> 자신을 상속받는 클래스를 정의
// MemberRepository: interface
// MemoryMemberRepository: MemberRepository를 상속받는 클래스, 이 클래스는 MemberRepostiory interface를 구현한다.
public interface MemberRepository {
    Member save(Member member); // 저장소에 회원 저장 , 회원 클래스 객체를 참조하기 위한 참조변수 member 선언 , 함수 호출

    //  Optional은 null 또는 값을 감싸서 NPE(NullPointerException)로부터 부담을 줄이기 위해 등장한 Wrapper 클래스
    // Wrapper class (래퍼 클래스) : 8개의 기본 타입에 해당하는 데이터를 객체로 표현하기 위해 포장해주는 클래스
    // 가져오는데 없으면 null, null을 optional로 감싸서 반환
    //
    Optional<Member> findById(Long id); // 저장소에서 회원을 찾아올 수 있음
    Optional<Member> findByName(String name);

    // List 인터페이스 : 정의된 메서드들 찾아서 ...
    // 인터페이스 List와 Set을 구현한 컬렉션 클래스들 -> collection 인터페이스 정의 가능 / map 인터페이스는 따로
    List<Member> findAll(); // 지금까지 저장된 모든 회원들 리스트 반환

}
