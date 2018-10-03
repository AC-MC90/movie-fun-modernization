package org.superbiz.moviefun.blobstore;

import java.io.InputStream;

public class Blob {
    public String name;
    public byte[] content;
    public String contentType;

    public Blob(){}

    public Blob(String name, byte[] content, String contentType) {
        this.name = name;
        this.content = content;
        this.contentType = contentType;
    }
}
