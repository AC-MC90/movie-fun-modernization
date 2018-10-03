package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriUtils;
import org.superbiz.moviefun.BlobInfo;

import java.io.IOException;
import java.net.URISyntaxException;
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

    public BlobInfo getCover(@PathVariable String blobId) throws Exception {
        return restOperations.getForObject(albumsUrl + "/{blobId}/cover", BlobInfo.class, UriUtils.encodePath(blobId,"UTF-8"));
    }

    public void uploadCover(@PathVariable String blobId, BlobInfo blob) throws Exception{
        restOperations.postForObject(albumsUrl + "/{blobId}/cover", blob, Void.class, UriUtils.encodePath(blobId,"UTF-8"));
    }
}
