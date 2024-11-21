package com.example.firstproject.service;

import com.example.firstproject.DTO.NoticeDTO;
import com.example.firstproject.entity.Notice;
import com.example.firstproject.respository.NoticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public Optional<Notice> index(Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        return notice;
    }

    public Notice info(Long id){
        Notice noticeList = noticeRepository.findById(id).orElse(null);
        return noticeList;
    }
    public List<NoticeDTO> list(){
        List<Notice> entity = noticeRepository.findAll();
        List<NoticeDTO> notice = new ArrayList<>();
        for(int i=0;i<entity.size();i++){
            Notice noticeEntity = entity.get(i);
            NoticeDTO noticeDTO = NoticeDTO.createNoticeDTO(noticeEntity);
            notice.add(noticeDTO);
        }
        return notice;
    }
    @Transactional
    public void create(NoticeDTO dto){
        Notice notice = dto.toEntity();
        noticeRepository.save(notice);
    }
    @Transactional
    public NoticeDTO update(Long id, NoticeDTO dto){
        Notice entity = dto.toEntity();
        Notice notice = noticeRepository.findByLongId(id);
        if(notice == null || id != notice.getId()){
            return null;
        }
        notice.patch(notice);
        Notice updated = noticeRepository.save(entity);
        return NoticeDTO.createNoticeDTO(updated);
    }
    @Transactional
    public Notice delete(Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾지 못했습니다."));
        noticeRepository.delete(notice);
        return notice;

    }
}
