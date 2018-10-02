package org.superbiz.moviefun.albums;

import org.springframework.web.client.RestOperations;

import java.util.List;

public class AlbumsClient {

    private final String albumsUrl;
    private final RestOperations restOperations;

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForObject(albumsUrl, album, Void.class);
    }

    public AlbumInfo find(long id) {
        return restOperations.getForObject(albumsUrl + "/{id}", AlbumInfo.class, id);
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.getForObject(albumsUrl, List.class);
    }

    public void deleteAlbum(AlbumInfo album) {
        restOperations.delete(albumsUrl, album);
    }

    public void updateAlbum(AlbumInfo album) {
        restOperations.put(albumsUrl, album);
    }
}
