package com.morgan.video_streaming.rest;
import com.morgan.video_streaming.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    //send the name of the videos to the frontend.
    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideoNames(){
        return ResponseEntity
                .ok(videoService.getAllVideoNames());
    }
}
