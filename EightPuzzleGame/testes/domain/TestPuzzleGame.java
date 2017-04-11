package domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.PuzzleGame;
import domain.ShufflePuzzleLevelEasy;
import domain.SquareBoard;

public class TestPuzzleGame {

	private PuzzleGame game;
	private int dimension;
	private SquareBoard board;

	@Before
	public void setup() {
		dimension = 3;
		game = new PuzzleGame(dimension, new ShufflePuzzleLevelEasy());
		board = new SquareBoard(dimension); // The board is always created with 3x3
	}

	// ------------------------------------------------------------------------------------
	// Testes de métodos que serão utilizados nos testes dos exercícios

	/**
	 * Criado para ter certeza de que a geração de peças está correta.
	 * @throws Exception
	 */
	@Test
	public void testNotNullTilesGeneration() throws Exception {
		List<Tile> tiles = game.generateListOfTiles(dimension * dimension - 1);
		assertNotNull(tiles);
	}

	@Test
	public void testTilesGeneration() throws Exception {
		List<Tile> tiles = game.generateListOfTiles(dimension * dimension - 1);
		assertEquals(dimension * dimension - 1, tiles.size());
	}

	// ------------------------------------------------------------------------------------

	@Test(expected = NullPointerException.class)
	public void testNullSquareBoard() throws Exception {
		List<Tile> tiles = game.generateListOfTiles(dimension * dimension - 1);
		game.putTilesInTheBoard(null, tiles);
	}

	@Test(expected = NullPointerException.class)
	public void testNullListOfTiles() throws Exception {
		game.putTilesInTheBoard(board, null);
	}

	// O método não verifica que a lista passada como parâmetro está vazia!
	@Test
	public void testEmptyBoard() throws Exception {
		List<Tile> tiles = new ArrayList<Tile>();
		game.putTilesInTheBoard(board, tiles);
		assertEquals(board.getTile(new Position(1, 1)), null);
	}

	@Test
	public void testTilesInPositions() throws Exception {
		List<Tile> tiles = game.generateListOfTiles(dimension * dimension - 1);
		game.putTilesInTheBoard(board, tiles);
		assertEquals(tiles.get(0), board.getTile(new Position(1,1)));
		assertEquals(tiles.get(1), board.getTile(new Position(1,2)));
		assertEquals(tiles.get(2), board.getTile(new Position(1,3)));
		assertEquals(tiles.get(3), board.getTile(new Position(2,1)));
		assertEquals(tiles.get(4), board.getTile(new Position(2,2)));
		assertEquals(tiles.get(5), board.getTile(new Position(2,3)));
		assertEquals(tiles.get(6), board.getTile(new Position(3,1)));
		assertEquals(tiles.get(7), board.getTile(new Position(3,2)));
	}

	// ------------------------------------------------------------------------------------
	// Testes de Fluxo de Controle
	
	@Test
	public void testPutTilesInTheBoard() throws Exception {
		
	}
}
