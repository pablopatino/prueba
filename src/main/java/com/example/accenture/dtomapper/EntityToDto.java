package com.example.accenture.dtomapper;

import com.example.accenture.domain.Photo;
import com.example.accenture.dto.PhotoDto;
import org.springframework.stereotype.Component;

@Component
public class EntityToDto {

    public PhotoDto photoEntityToDto(Photo photo){
        PhotoDto photoDto = new PhotoDto();
        photoDto.setTitle(photo.getTitle());
        photoDto.setUrl(photo.getUrl());
        photoDto.setThumbnailUrl(photo.getThumbnailUrl());
        return photoDto;
    }

}
