package com.miracle.sport.community.bean;

/**
 * Created by Michael on 2018/10/27 15:43 (星期六)
 */
public class PostBean {

    /**
     * id : 3
     * title : 东方
     * thumb : http://xiaozhuang.988lhkj.com/uploads/20181027/522ec1ba72cd986bbc913e21005f84fe.JPG
     * click_num : 1
     * comment_num : 0
     * total : 1
     * add_time : 2018-10-29 20:37:48
     * nickname : 颜色不一样的烟火
     * name : 湖人
     */

    private int id;
    private String title;
    private String thumb;
    private int click_num;
    private int comment_num;
    private int total;
    private String add_time;
    private String nickname;
    private String name;

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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
