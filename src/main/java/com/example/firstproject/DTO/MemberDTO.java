package com.example.firstproject.DTO;
import com.example.firstproject.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberDTO {
    private String email;
    private String name;
    private String password;

    public MemberEntity toEntity(){
        //id 필드가 없기 때문에 null, 있으면 작성
        return new MemberEntity(email, name, password); //dto 개념
    }
}
