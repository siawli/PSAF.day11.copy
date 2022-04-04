package vttp2022.paf.day1144.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.day1144.model.Comment;
import vttp2022.paf.day1144.model.Game;

import static vttp2022.paf.day1144.repositories.Queries.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {
    
    @Autowired
    private JdbcTemplate template;

    public Optional<Game> getGameById(Integer queryGid) {
        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_GAME_BY_GID, queryGid);
        /* 
        here querying primary key - so we know it will return 1
        but can actually return many depending on our query
        result is in the form of a table
        point starts right at the top
        .next() goes to the next row and returns true 
        until it .next() has no more rows -> return false
        if query has no results -> .next() would be false immediately
        */

        if (!result.next()) {
            return Optional.empty();
        }

        Game game = Game.create(result);

        return Optional.of(game);

    }

    public List<Comment> getCommentsByGid(Integer gid) {
        return getCommentsByGid(gid, Integer.MAX_VALUE, 0);
    }

    public List<Comment> getCommentsByGid(Integer gid, Integer limit) {
        return getCommentsByGid(gid, limit, 0);
    }

    public List<Comment> getCommentsByGid(Integer gid, Integer limit, Integer offset) {
        // if no comments, list would be zero

        final SqlRowSet sqlResult = 
            template.queryForRowSet(SQL_SELECT_COMMENT_BY_GID, gid, limit, offset);
        
        List<Comment> comments = new LinkedList<>();

        while (sqlResult.next()) {
            Comment comment = new Comment();
            // Comment comment = Comment.create(result);
            // store the below in the Comment class
            comment.setcId(sqlResult.getString("c_id"));
            comment.setUser(sqlResult.getString("user"));
            comment.setRating(sqlResult.getInt("rating"));
            comment.setcText(sqlResult.getString("c_text"));
            comment.setGid(sqlResult.getInt("gid"));

            // comment.setGid(sqlResult.getInt(5));
            // comment.setRating(sqlResult.getInt(3));
            // comment.setUser(sqlResult.getString(2));
            // comment.setcId(sqlResult.getString(1));
            // comment.setcText(sqlResult.getString(4));
            comments.add(comment);
            }

        return comments;
    }
}
