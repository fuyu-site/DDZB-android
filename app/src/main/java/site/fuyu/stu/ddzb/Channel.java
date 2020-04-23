package site.fuyu.stu.ddzb;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 频道模型对象
 */
public class Channel implements Serializable {
    private String title;
    private String quality;
    private String cover;
    private String url;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return cover == channel.cover &&
                Objects.equals(title, channel.title) &&
                Objects.equals(quality, channel.quality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, quality, cover);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", quality='" + quality + '\'' +
                ", cover=" + cover +
                ", url='" + url + '\'' +
                '}';
    }
}
