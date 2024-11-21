package com.example.firstproject.api;

import com.example.firstproject.DTO.NoticeDTO;
import com.example.firstproject.entity.Notice;
import com.example.firstproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class ApiNoticeController {
    @Autowired
    NoticeService noticeService;
    /*
    //1. 공지사항 목록 조회
    @GetMapping(value = {"","/"})
    public ResponseEntity<List<NoticeDTO>> list(Model model) {
        List<NoticeDTO> list = noticeService.list();
        if (list == null || list.isEmpty()) {
            model.addAttribute("noticeDTO", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            ResponseEntity.status(HttpStatus.OK).body(list);
            model.addAttribute("noticeDTO", list);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    //@PostMapping("/{id}/create")
    //public ResponseEntity<NoticeDTO> create(@PathVariable Long id,
                                            @RequestBody NoticeDTO dto){
       // NoticeDTO createdDTO = noticeService.create(dto);
        //return ResponseEntity.status(HttpStatus.OK).body(createdDTO);
    //}
    @PatchMapping("/{id}")
    public ResponseEntity<NoticeDTO> update(@PathVariable Long id,
                                            @RequestBody NoticeDTO dto){
        NoticeDTO updatedDTO = noticeService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<NoticeDTO> delete(@PathVariable Long id){

        NoticeDTO noticeDTO = noticeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(noticeDTO);
    }
    */
}
