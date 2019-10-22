package se.chalmers.cse.dit341.group07.model;

public class Review {
    private String id;
    private String text;
    private String date;
    private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Review(String id, String text, String date, int rating) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.rating = rating;
    }

}
