package org.superbiz.moviefun.albums;

import org.apache.tika.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AlbumsRepository albumsRepository;
    private final BlobStoreClient blobStoreClient;
    
    public AlbumsController(AlbumsRepository albumsRepository,
                            BlobStoreClient blobStoreClient) {
        this.albumsRepository = albumsRepository;
        this.blobStoreClient = blobStoreClient;
    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        albumsRepository.addAlbum(album);
    }

    @GetMapping("/{id}")
    public Album find(@PathVariable("id") long id) {
        return albumsRepository.find(id);
    }

    @GetMapping
    public List<Album> getAlbums() {
        return albumsRepository.getAlbums();
    }

    @DeleteMapping
    public void deleteAlbum(@RequestBody Album album) {
        albumsRepository.deleteAlbum(album);
    }

    @PutMapping
    public void updateAlbum(@RequestBody Album album) {
        albumsRepository.updateAlbum(album);
    }

    @GetMapping("/{blobId}/cover")
    public BlobInfo getCover(@PathVariable String blobId) throws IOException, URISyntaxException {
        Optional<BlobInfo> maybeCoverBlob = blobStoreClient.get(blobId);
        BlobInfo coverBlob = maybeCoverBlob.orElseGet(this::buildDefaultCoverBlob);

        return coverBlob;
    }

    @PostMapping("/{blobId}/cover")
    public void uploadCover(@PathVariable String blobId, @RequestBody BlobInfo blob) {
        logger.debug("Uploading cover for album with id {}", blobId);
        try {
            blobStoreClient.put(blob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BlobInfo buildDefaultCoverBlob() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input = classLoader.getResourceAsStream("default-cover.jpg");
        try {
            return new BlobInfo("default-cover", IOUtils.toByteArray(input), MediaType.IMAGE_JPEG_VALUE);
        }catch (IOException ioEX){
            //Ignore (bad idea, but this is a lab so whatever)
            return null;
        }
    }




}
