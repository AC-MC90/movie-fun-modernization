package org.superbiz.moviefun.blobstore;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/blobstore")
public class BlobStoreController {

    private final BlobStore blobStore;

    public BlobStoreController(BlobStore blobStore) {
        this.blobStore = blobStore;
    }

    @PostMapping
    public void put(@RequestBody Blob blob) throws IOException{
        blobStore.put(blob);
    }

    @GetMapping("/{id}")
    public Blob get(@PathVariable String id) throws IOException{
        Optional<Blob> maybeBlob = blobStore.get(id);
        if(maybeBlob.isPresent()){
            return maybeBlob.get();
        }else{
            return null;
        }
    }

}
