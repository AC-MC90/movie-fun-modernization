package org.superbiz.moviefun.albums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.Optional;

@Component
public class BlobStoreClient {

    private final String blobStoreUrl;
    private final RestOperations restOperations;

    public BlobStoreClient(@Value("${blobStore.url}") String blobStoreUrl,
                           RestOperations restOperations) {
        this.blobStoreUrl = blobStoreUrl;
        this.restOperations = restOperations;
    }

    public void put(BlobInfo blob) throws IOException {
        restOperations.postForObject(blobStoreUrl, blob, Void.class);
    }

    public Optional<BlobInfo> get(@PathVariable String id) throws IOException{
        BlobInfo blobInfo = restOperations.getForObject(blobStoreUrl + "/{id}", BlobInfo.class, id);
        return Optional.ofNullable(blobInfo);
    }
}
