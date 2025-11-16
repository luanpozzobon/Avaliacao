package models;

public record NFeDocument(String id,
                          String content) {
    @Override
    public String toString() {
        return "NFeDocument{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
