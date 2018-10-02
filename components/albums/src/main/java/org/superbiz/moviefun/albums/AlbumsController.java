package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumsRepository albumsRepository;
    
    public AlbumsController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @PostMapping
    public void addAlbum(Album album) {
        albumsRepository.addAlbum(album);
    }

    @GetMapping("/{id}")
    public Album find(long id) {
        return albumsRepository.find(id);
    }

    @GetMapping
    public List<Album> getAlbums() {
        return albumsRepository.getAlbums();
    }

    @DeleteMapping
    public void deleteAlbum(Album album) {
        albumsRepository.deleteAlbum(album);
    }

    @PutMapping
    public void updateAlbum(Album album) {
        albumsRepository.updateAlbum(album);
    }
}
