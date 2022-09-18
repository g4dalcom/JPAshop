package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Member {

    @GeneratedValue
    @Id
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;
                                    // Order테이블에 있는 member 테이블에 의해 mapped됨(연관관계 거울)
    @OneToMany(mappedBy = "member") // 멤버 입장에서 하나의 회원이 여러개의 order를 하므로 1:N
    private List<Order> orders = new ArrayList<>();

}
