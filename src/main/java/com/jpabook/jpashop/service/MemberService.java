package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/* 회원 조회 부분은 Transactional(readOnly = true)를 붙이는 것이 성능면에서 이점이 있음
* 현재 세팅은 기본적으로 readOnly = true를 하되 데이터 변경이 필요한 곳은 추가로 @Transactional을 붙여줌 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        /* 중복회원 검증 */
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        /* 회원 수를 검색해서 0보다 크면 문제가 있다는 식으로 하면 더 최적화가 될 수 있음 */
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /* 회원 전체 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /* 회원 단건 조회 */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
