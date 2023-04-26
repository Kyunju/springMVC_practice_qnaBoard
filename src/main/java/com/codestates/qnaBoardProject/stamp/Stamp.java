package com.codestates.qnaBoardProject.stamp;

import com.codestates.qnaBoardProject.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stampId;
    @Column(nullable = false)
    private int stampCount;
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    public void setMember(Member member) {
        this.member = member;
        if (member.getStamp() != this) {
            member.setStamp(this);
        }
    }
}
