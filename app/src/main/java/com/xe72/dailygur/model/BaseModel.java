package com.xe72.dailygur.model;

import com.annimon.stream.Stream;
import com.xe72.dailygur.dto.InnerData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class BaseModel {

    private ServerApi serverApi;

    public BaseModel(ServerApi serverApi) {
        this.serverApi = serverApi;
    }

    public Observable<List<InnerData>> loadGallery(int page) {
        return serverApi.getGallery(page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(allAlbum -> {
                    List<InnerData> imageList = Stream.of(allAlbum.getData()).map(gallery -> {
                        String id = gallery.getId();
                        String imageLink;
                        Boolean album = gallery.getIsAlbum();
                        Integer coverWidth;
                        Integer coverHeight;
                        if (gallery.getIsAlbum()) {
                            imageLink = gallery.getImages().get(0).getLink();
                            coverWidth = gallery.getImages().get(0).getWidth();
                            coverHeight = gallery.getImages().get(0).getHeight();
                        } else {
                            imageLink = gallery.getLink();
                            coverWidth = gallery.getWidth();
                            coverHeight = gallery.getHeight();
                        }
                        return new InnerData(id, imageLink, album, coverWidth, coverHeight);
                    }).toList();
                    return imageList;
                });
    }
}
