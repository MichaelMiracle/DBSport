package com.miracle.sport.community.bean;

import java.util.List;

/**
 * Created by Michael on 2018/10/30 21:29 (星期二)
 */
public class PostDetailBean {


    /**
     * id : 3
     * title : 东方
     * content : 东方
     * thumb : ["http://xiaozhuang.988lhkj.com/uploads/20181027/522ec1ba72cd986bbc913e21005f84fe.JPG","http://xiaozhuang.988lhkj.com/uploads/20181027/522ec1ba72cd986bbc913e21005f84fe.JPG"]
     * click_num : 1
     * comment_num : 0
     * circle_id : 8
     * name : 马刺
     * click : 0
     */

    private int id;
    private String title;
    private String content;
    private int click_num;
    private int comment_num;
    private int circle_id;
    private String name;
    private int click;
    private List<String> thumb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getClick_num() {
        return click_num;
    }

    public void setClick_num(int click_num) {
        this.click_num = click_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getCircle_id() {
        return circle_id;
    }

    public void setCircle_id(int circle_id) {
        this.circle_id = circle_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public List<String> getThumb() {
        return thumb;
    }

    public void setThumb(List<String> thumb) {
        this.thumb = thumb;
    }
}
