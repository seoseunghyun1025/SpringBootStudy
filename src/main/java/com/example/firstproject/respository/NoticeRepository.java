package com.example.firstproject.respository;

import com.example.firstproject.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value="Select * from notice where id = :id",nativeQuery = true)
    Notice findByLongId(Long id);
}
