package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

import java.util.Optional;

@Component
public class MemberServiceImpl implements MemberService{

        
      //의존관계 주입 : 필드 이용
      //@Autowired private MemberRepository memberRepository;
      private final MemberRepository memberRepository;

//      @Autowired //생성자가 1개일 땐, 안 써줘도 자동 주입
      public MemberServiceImpl(MemberRepository memberRepository) {
          this.memberRepository = memberRepository;
      }

//    @Autowired //의존관계 주입 : setMethod 이용 (자바빈 프로퍼티 사용)
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
