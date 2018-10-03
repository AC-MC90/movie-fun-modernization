package org.superbiz.moviefun;

public class BlobInfo {

    private String name;
    private byte[] content;
    private String contentType;

    public BlobInfo() {
    }

    public BlobInfo(String name, byte[] content, String contentType) {
        this.name = name;
        this.content = content;
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlobInfo blobInfo = (BlobInfo) o;

        if (name != null ? !name.equals(blobInfo.name) : blobInfo.name != null) return false;
        if (content != null ? !content.equals(blobInfo.content) : blobInfo.content != null)
            return false;
        return contentType != null ? contentType.equals(blobInfo.contentType) : blobInfo.contentType == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        return result;
    }
}
