package vttp2022.paf.day1144.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vttp2022.paf.day1144.model.Comment;
import vttp2022.paf.day1144.model.Game;
import vttp2022.paf.day1144.repositories.GameRepository;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepo;
    
    @GetMapping("/game/{gid}")
    public String returnGameDetails(@PathVariable Integer gid, Model model) {
        Optional<Game> game = gameRepo.getGameById(gid);
        if (game.isPresent()) {
            Game gameObtained = game.get();
            model.addAttribute("game", gameObtained);

            List<Comment> comments = gameRepo.getCommentsByGid(gid);
            model.addAttribute("comments", comments);

            return "gameDetails";
        } else {
            return "error";
        }
    }
}
