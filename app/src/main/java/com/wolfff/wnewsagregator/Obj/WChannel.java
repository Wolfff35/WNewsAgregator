package com.wolfff.wnewsagregator.Obj;

/**
 * Created by wolff on 21.10.2016.
 */

public class WChannel {
/*
    language        – Язык канала.
    copyright       – Авторские права на канал.
    managingEditor  – Адрес электронной почты редактора данного канала.
    webMaster       – Адрес электронной почты администратора сайта, на котором расположен канал.
    pubDate         – Дата публикации содержания в канале.
    lastBuildDate   – Дата последнего изменения содержания в канале.
    category        – Позволяет добавлять одну или несколько категорий, к которым принадлежит канал.
    generator       – Программа-генератор, которая создала канал.
    docs            – Ссылки на документацию в формате RSS ленты.
    cloud           – Обеспечивает процесс регистрации в «облако», которое будет использоваться для уведомления об обновлениях.
    ttl             – Время жизни канала в кэше в минутах.
    image           – Файл изображения, которое будет отображаться в канале.
    rating          – PICS рейтинга канала.
    textInput       – Текстовое поле ввода, которое позволяет пользователям реагировать на канал.
    skipHours       – Сообщает агрегаторам (программам читающим RSS-ленты), в какое время мы их не хотим видеть.
    skipDays        – Сообщает агрегаторам, в какие дни они не должны нас беспокоить.
 */
    private long id;
    private String title;
    private String link;
    private long idGroup;

    private String description;
    private String pubDate;
    private String image;

    public WChannel(long id,String title, String link,long idGroup,String description, String pubDate,String image){
        this.id = id;
        this.title = title;
        this.link = link;
        this.idGroup = idGroup;

        this.description=description;
        this.pubDate = pubDate;
        this.image = image;
    }
    public long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
