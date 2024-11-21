package com.example.firstproject.respository;

import com.example.firstproject.DTO.MemberDTO;
import com.example.firstproject.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberEntity, String> {
}
