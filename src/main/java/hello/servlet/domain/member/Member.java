package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id; //db에 저장하면 자동으로 발금
    private String username;
    private int age;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member() { //기본 생성자
    }
}
