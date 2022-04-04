package vttp2022.paf.day1144;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.day1144.model.Comment;
import vttp2022.paf.day1144.model.Game;
import vttp2022.paf.day1144.repositories.GameRepository;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private GameRepository gameRepo;

	@Test
	void shouldReturnAGame() {
		Optional<Game> opt = gameRepo.getGameById(10);
		assertTrue(opt.isPresent(), "gid = 10");
	}

	@Test
	void shouldReturnEmpty() {
		Optional<Game> opt = gameRepo.getGameById(10000);
		assertFalse(opt.isPresent(), "gid = 10000");
	}

	@Test
	void shouldReturn42Comments() {
		List<Comment> comments = gameRepo.getCommentsByGid(10);
		// assertTrue(comments.size() == 42);
		assertEquals(42, comments.size());
	}

	@Test
	void shouldReturnEmptyList() {
		List<Comment> comments = gameRepo.getCommentsByGid(000, 10, 0);
		// assertTrue(comments.size() == 0);
		assertEquals(0, comments.size());
	}

}
