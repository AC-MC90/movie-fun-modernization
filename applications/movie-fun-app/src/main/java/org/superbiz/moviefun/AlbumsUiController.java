package org.superbiz.moviefun;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.superbiz.moviefun.albums.AlbumsClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static java.lang.String.format;

@Controller
@RequestMapping("/albums")
public class AlbumsUiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AlbumsClient albumsClient;

    public AlbumsUiController(AlbumsClient albumsClient) {
        this.albumsClient = albumsClient;
    }


    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("albums", albumsClient.getAlbums());
        return "albums";
    }

    @GetMapping("/{albumId}")
    public String details(@PathVariable long albumId, Map<String, Object> model) {
        model.put("album", albumsClient.find(albumId));
        return "albumDetails";
    }

    @GetMapping("/{albumId}/cover")
    public HttpEntity<byte[]> getCover(@PathVariable long albumId) throws Exception {

        BlobInfo cover = albumsClient.getCover(Long.toString(albumId));

        byte[] imageBytes = cover.getContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(cover.getContentType()));
        headers.setContentLength(imageBytes.length);

        return new HttpEntity<byte[]>(imageBytes, headers);
    }

    @PostMapping("/{albumId}/cover")
    public String uploadCover(@PathVariable Long albumId, @RequestParam("file") MultipartFile uploadedFile) throws Exception{
        if (uploadedFile.getSize() > 0) {
            BlobInfo coverBlob = new BlobInfo(
                    Long.toString(albumId),
                    IOUtils.toByteArray(uploadedFile.getInputStream()),
                    uploadedFile.getContentType()
            );
            albumsClient.uploadCover(Long.toString(albumId), coverBlob);
            return format("redirect:/albums/%d", albumId);
        }else{
            logger.warn("Problem when attempting to upload file");
            return ""; //FIXME should return something better
        }
    }
}
