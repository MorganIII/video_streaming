package com.morgan.video_streaming.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "video_spec")
@Data
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "video_name",unique = true)
    private String name;

    @Lob
    @Column(name = "video")
    private byte[] video;


    public Video(String name, byte[] video) {
        this.name = name;
        this.video = video;
    }



}
