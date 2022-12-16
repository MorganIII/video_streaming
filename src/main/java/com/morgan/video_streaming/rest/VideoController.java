package com.morgan.video_streaming.rest;


import com.morgan.video_streaming.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("video")
public class VideoController {

    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }



    @GetMapping("{name}")
    public ResponseEntity<Resource> getVideoByName(@PathVariable("name") String name){
        return ResponseEntity
                .ok(new ByteArrayResource(videoService.getVideo(name).getVideo()));
    }

    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideoNames(){
        return ResponseEntity
                .ok(videoService.getAllVideoNames());
    }
}
