package com.morgan.video_streaming.rest;
import com.morgan.video_streaming.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@RestController
@RequestMapping("video")
@CrossOrigin
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

@GetMapping(value = "{name}")
@ResponseBody
public ResponseEntity<StreamingResponseBody> getVideo(
        @PathVariable
        String name,
        @RequestHeader(value = "Range", required = false)
        String rangeHeader)
{
     byte[] videoBytes =videoService.getVideo(name).getVideo();

        if (rangeHeader == null)
        {
            return videoService.headerNull(videoBytes);
        }

        return videoService.headerNotNull(videoBytes,rangeHeader);

}


    //send the name of the videos to the frontend.
    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideoNames(){
        return ResponseEntity
                .ok(videoService.getAllVideoNames());
    }
}
