package com.example.abdul_wadudmusa.drawer;
public class News {
    String Topic;
    String webpage;
    String ImageUrl;

    public News(String topic, String webpage, String imageUrl) {
        Topic = topic;
        this.webpage = webpage;
        ImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
    public String getWebpage() {
        return webpage;
    }
    public String getTopic() {
        return Topic;
    }







}
