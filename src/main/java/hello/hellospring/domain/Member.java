package hello.hellospring.domain;


public class Member {
    // 비즈니스 요구사항 : 데이터 - 회원 ID, name
    private long id; // Id: 임의의 값; 고객이 정하는 것이 아니라 시스템에서 정하는 ID
    private String name;

    public Long getID() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
