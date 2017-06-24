package com.example.alejandramonteon.newsapp;

public class News {

    private String author;
    private String title;
    private String url;
    private String description;
    private String urlToImage;
    private String publishedAt;

    public News(String author, String title, String url, String description, String urlToImage, String publishedAt){
        this.author = author;
        this.title = title;
        this.url = url;
        this. description = description;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
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

    public String getPublishedAt(){
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt){
        this.publishedAt = publishedAt;
    }

}

