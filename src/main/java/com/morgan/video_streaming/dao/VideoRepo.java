package com.morgan.video_streaming.dao;

import com.morgan.video_streaming.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideoRepo extends JpaRepository<Video,Integer> {

    Video findByName(String name);

    boolean existsByName(String name);

    @Query(nativeQuery = true, value="SELECT video_name FROM video_spec")
    List<String> getAllEntryNames();


}
