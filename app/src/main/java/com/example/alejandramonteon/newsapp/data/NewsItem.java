package com.example.alejandramonteon.newsapp.data;

public class NewsItem {

    private String author;
    private String title;
    private String url;
    private String description;
    private String urlToImage;
    private String published_date;

    public NewsItem(String author, String title, String url, String description, String urlToImage, String published_date){
        this.author = author;
        this.title = title;
        this.url = url;
        this. description = description;
        this.urlToImage = urlToImage;
        this.published_date = published_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrltoImage(){
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage){
        this.urlToImage = urlToImage;

    }

    public String getPublished_date(){
        return published_date;
    }

    public void setPublished_date(String published_date){
        this.published_date = published_date;
    }

}

