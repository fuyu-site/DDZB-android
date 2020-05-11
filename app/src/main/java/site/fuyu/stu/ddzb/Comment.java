package site.fuyu.stu.ddzb;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Comment implements Serializable {
    private String content;
    private String author;
    private LocalDateTime dateTime;
    private int star;


    @NotNull
    @Override
    public String toString() {
        return "Comments{" +
                "content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", dateTime=" + dateTime +
                ", star=" + star +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return star == comment.star &&
                Objects.equals(content, comment.content) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(dateTime, comment.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, author, dateTime, star);
    }

    String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
