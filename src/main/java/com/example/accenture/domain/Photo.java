package com.example.accenture.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Photo {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
