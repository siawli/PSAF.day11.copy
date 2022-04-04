package vttp2022.paf.day1144.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {

    public Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer userRated;
    private String url;
    private String image;

    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUserRated() {
        return userRated;
    }
    public void setUserRated(Integer userRated) {
        this.userRated = userRated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


    public static Game create(SqlRowSet result) {
        Game game = new Game();

        game.setGid(result.getInt("gid"));
        game.setName(result.getString("name"));
        game.setYear(result.getInt("year"));
        game.setRanking(result.getInt("ranking"));
        game.setUserRated(result.getInt("users_rated"));
        game.setUrl(result.getString("url"));
        game.setImage(result.getString("image"));
        // game.setGid(result.getInt(1));
        // game.setName(result.getString(2));
        // game.setYear(result.getInt(3));
        // game.setRanking(result.getInt(4));
        // game.setUserRated(result.getInt(5));
        // game.setUrl(result.getString(6));
        // game.setImage(result.getString(7));

        return game;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("gid", getGid())
                .add("name", getName())
                .add("year", getYear())
                .add("ranking", getRanking())
                .add("userRated", getUserRated())
                .add("url", getUrl())
                .add("image", getImage())
                .build();
    }



    
    
}
