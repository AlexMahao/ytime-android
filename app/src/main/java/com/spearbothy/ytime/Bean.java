package com.spearbothy.ytime;

/**
 * Created by mahao on 17-5-9.
 */

public class Bean {

    /**
     * title : 鐙愮嫺鎱ц禋涓昏瑙�
     * link :
     * picture : https://help.souyidai.com/upload/2017/04/14/1492164229833.jpg
     */

    private String title;
    private String link;
    private String picture;

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public String getLink() { return link;}

    public void setLink(String link) { this.link = link;}

    public String getPicture() { return picture;}

    public void setPicture(String picture) { this.picture = picture;}

    @Override
    public String toString() {
        return "Bean{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
