package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter //모든 필드에 대한 getter를 정의
@ToString
@Entity
public class MemberEntity {
    @Id
    private String email;
    @Column
    private String name;
    @Column
    private String password;

    public void patch(MemberEntity member) {
        if(member.email != null){
            this.email = member.email;
        }
        if(member.name != null){
            this.name = member.name;
        }
        if(member.password != null){
            this.password = member.password;
        }
    }
}
