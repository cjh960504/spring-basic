package hello.core.member;

        import java.util.HashMap;
        import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    //저장소의 개념이니까 키와 밸류의 형태
    //동시성 이슈로 인하여 실무에선 ConcurrentHashMap을 사용해야함 (학습필요)
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
