package com.cafe24.demo.DAO;

import com.cafe24.demo.VO.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDAO extends JpaRepository<Video, String> {

    
}