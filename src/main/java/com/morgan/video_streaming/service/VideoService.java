package com.morgan.video_streaming.service;


import com.morgan.video_streaming.entity.Video;

import java.util.List;

public interface VideoService {

    Video getVideo(String name);


    List<String> getAllVideoNames();


}
