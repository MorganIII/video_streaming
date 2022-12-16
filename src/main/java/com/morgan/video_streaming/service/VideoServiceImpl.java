package com.morgan.video_streaming.service;

import com.morgan.video_streaming.dao.VideoRepo;
import com.morgan.video_streaming.entity.Video;
import com.morgan.video_streaming.exceptions.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{
    private VideoRepo repo;

    @Autowired
    public VideoServiceImpl(VideoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Video getVideo(String name) {
        if(!repo.existsByName(name)){
            throw new VideoNotFoundException();
        }
        return repo.findByName(name);
    }

    @Override
    public List<String> getAllVideoNames() {
        return repo.getAllEntryNames();
    }


}
