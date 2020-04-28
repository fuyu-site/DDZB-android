package site.fuyu.stu.ddzb;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public class Comments {
    private String content;
    private String author;
    private Date dateTime;
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
        Comments comments = (Comments) o;
        return star == comments.star &&
                Objects.equals(content, comments.content) &&
                Objects.equals(author, comments.author) &&
                Objects.equals(dateTime, comments.dateTime);
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
