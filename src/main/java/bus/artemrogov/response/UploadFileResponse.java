package bus.artemrogov.response;

public class UploadFileResponse {
    private String fileOriginName;
    private String filePath;
    private Long Size;
    private String contentType;

    public UploadFileResponse(String fileOriginName, String filePath, Long size, String contentType) {
        this.fileOriginName = fileOriginName;
        this.filePath = filePath;
        Size = size;
        this.contentType = contentType;
    }

    public String getFileOriginName() {
        return fileOriginName;
    }

    public void setFileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getSize() {
        return Size;
    }

    public void setSize(Long size) {
        Size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
