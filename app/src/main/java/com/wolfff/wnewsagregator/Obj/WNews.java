package com.wolfff.wnewsagregator.Obj;

/**
 * Created by wolff on 21.10.2016.
 */

public class WNews {
    /*
    guid        – Уникальный идентификатор элемента item.
    pubDate     – Дата публикации элемента.
    title       – Заголовок элемента. В нашем случае он совпадает с заголовком публикуемой записи в интернет-дневнике.
    description – содержит основные данные элемента. В нашем случае это тело записи в блоге.
    link        – Содержит полный URL адрес до страницы, на которой данный элемент представлен максимально подробно.
    author      – Автор этой записи.
    category    – Позволяет поместить элемент в одну или более категорий.
    comments    — Ссылка на страницу, где можно оставлять комментарии к этой записи.
    enclosure   – Может быть использован для описания медиа объекта, прикрепленного к элементу.
    source      – Ссылка на RSS-канал, откуда был взят этот элемент.
            */

    private String guid;
    private String pubDate;
    private String title;
    private String description;
    private String link;
    private String enclosure;
    private int isRead;
    private long idChannel;
    private long id;
    long date;

    public WNews(long id, String guid, String pubDate, String title, String description, String link, String enclosure, int isRead, long idChannel, long date){
        this.id = id;
        this.guid = guid;
        this.pubDate = pubDate;
        this.title = title;
        this.description = description;
        this.link = link;
        this.enclosure = enclosure;
        this.isRead = isRead;
        this.idChannel = idChannel;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(long idChannel) {
        this.idChannel = idChannel;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }


}
