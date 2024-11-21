package com.example.firstproject.entity;

import com.example.firstproject.DTO.NoticeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String body;

    public void patch(Notice dto){
        if(this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패");
        }if(dto.getTitle() != null){
            this.title = dto.getTitle();
        }if(dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}
