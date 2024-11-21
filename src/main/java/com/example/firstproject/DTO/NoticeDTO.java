package com.example.firstproject.DTO;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Notice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class NoticeDTO {
    private Long id;
    private String title;
    private String body;

    public Notice toEntity(){
        //id 필드가 없기 때문에 null, 있으면 작성
        return new Notice(id,title, body); //dto 개념
    }
    public static NoticeDTO createNoticeDTO(Notice notice) {
        return new NoticeDTO(
                notice.getId(),
                notice.getTitle(),
                notice.getBody());
    }
}
