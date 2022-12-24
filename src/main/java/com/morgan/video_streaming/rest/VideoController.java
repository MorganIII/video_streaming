package com.morgan.video_streaming.rest;
import com.morgan.video_streaming.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

        StreamingResponseBody responseStream;
        byte[] videoBytes =videoService.getVideo(name).getVideo();
        Long videoSize = Long.valueOf(videoBytes.length);
        byte[] buffer = new byte[5120];
        final HttpHeaders responseHeaders = new HttpHeaders();

        if (rangeHeader == null)
        {
            responseHeaders.add("Content-Type", "video/mp4");
            responseHeaders.add("Content-Length", videoSize.toString());
            responseStream = os -> {
                int pos = 0;
                while(pos<videoBytes.length){
                    System.arraycopy(videoBytes,pos,buffer,0,5120);
                    os.write(buffer);
                    pos += buffer.length;
                }
                os.flush();
            };

            return new ResponseEntity<>
                    (responseStream, responseHeaders, HttpStatus.OK);
        }

        String[] ranges = rangeHeader.split("-");
        Long rangeStart = Long.parseLong(ranges[0].substring(6));
        Long rangeEnd;
        if (ranges.length > 1)
        {
            rangeEnd = Long.parseLong(ranges[1]);
        }
        else
        {
            rangeEnd = videoSize - 1;
        }

        if (videoSize < rangeEnd)
        {
            rangeEnd = videoSize - 1;
        }

        String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
        responseHeaders.add("Content-Type", "video/mp4");
        responseHeaders.add("Content-Length", contentLength);
        responseHeaders.add("Accept-Ranges", "bytes");
        responseHeaders.add("Content-Range", "bytes" + " " +
                rangeStart + "-" + rangeEnd + "/" + videoSize);
        final Long _rangeEnd = rangeEnd;
        responseStream = os -> {
            long pos = rangeStart;
            while(pos<_rangeEnd){
                System.arraycopy(videoBytes, (int) pos,buffer,0,5120);
                os.write(buffer);
                pos += buffer.length;
            }
            os.flush();
        };

        return new ResponseEntity<>
                (responseStream, responseHeaders, HttpStatus.PARTIAL_CONTENT);

}



    //send the name of the videos to the frontend.
    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideoNames(){
        return ResponseEntity
                .ok(videoService.getAllVideoNames());
    }
}
