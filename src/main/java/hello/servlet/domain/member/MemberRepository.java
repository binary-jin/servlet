package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //아이디가 하나씩 증가
    //static으로 받았기 때문에 아무리 new로 생성해도 store과 sequence는 하나씩만 생성이

    //싱글톤으로 만듬
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() { //무조건 얘를 통해 조회
        return instance;
    }

    //싱글톤으로 만들 때는 생성자로 일단 막아야함 -> 아무나 생성하지 못하게
    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(++sequence); //id 값 자동 증가
        store.put(member.getId(), member); //증가 된 id 값을 store에 저장함
        return member;
    }

    public Member findById(Long id) {
        return store.get(id); //id로 받은 값을 리턴
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 모든 값을 ArrayList에 담아 넘겨줌
    }

    public void clearStore() {
        store.clear(); //테스트에서 사용 할 때 스토어를 날려버림
    }
}
