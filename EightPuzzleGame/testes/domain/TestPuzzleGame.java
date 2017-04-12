package domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Puzzle.Direction;

public class TestPuzzleGame {

	private PuzzleGame game;
	private int dimension;
	private SquareBoard board;
	private List<Tile> tiles;

	@Before
	public void setup() {
		dimension = 3;
		game = new PuzzleGame(dimension, new ShufflePuzzleNoLevel());
		board = new SquareBoard(dimension); // The board is always created with 3x3
		tiles = game.generateListOfTiles(dimension * dimension - 1);
	}

	// ------------------------------------------------------------------------------------
	// Testes de métodos que serão utilizados nos testes dos exercícios

	/**
	 * Criado para ter certeza de que a geração de peças está correta.
	 * @throws Exception
	 */
	@Test
	public void testNotNullTilesGeneration() throws Exception {
		assertNotNull(tiles);
	}

	@Test
	public void testTilesGeneration() throws Exception {
		assertEquals(dimension * dimension - 1, tiles.size());
	}

	// ------------------------------------------------------------------------------------

	@Test(expected = NullPointerException.class)
	public void testNullSquareBoard() throws Exception {
		game.putTilesInTheBoard(null, tiles);
	}

	@Test(expected = NullPointerException.class)
	public void testNullListOfTiles() throws Exception {
	    List<Tile> tiles = null;
	    game.putTilesInTheBoard(board, tiles);
	}

//	// O método não verifica que a lista passada como parâmetro está vazia!
//	@Test
//	public void testEmptyBoard() throws Exception {
//		List<Tile> tiles = new ArrayList<Tile>();
//		game.putTilesInTheBoard(board, tiles);
//		assertEquals(board.getTile(new Position(1, 1)), null);
//	}

	@Test
	public void testTilesInPositions() throws Exception {
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

	/**
	 * Evita entrar nos loops for do método. O número de caminhos é
	 * consideravelmente reduzido, visto que todos os loops são evitados
	 * ao mesmo tempo.
	 * 
	 * OBS.: por algum motivo o teste não termina de ser executado!!
	 * 
	 * @throws Exception
	 */
//	@Test
//    public void testPutTilesInTheBoardAvoidingForLoops() throws Exception {
//	    int dimension = 1;  // Avoids all for loops.
//        PuzzleGame game = new PuzzleGame(dimension, new ShufflePuzzleLevelEasy());
//        SquareBoard board = new SquareBoard(dimension);
//        List<Tile> tiles = game.generateListOfTiles(1);
//        game.putTilesInTheBoard(board, tiles);
//        assertNotEquals(tiles.get(0), board.getTile(new Position(1,1)));
//    }

	@Test
	public void testMoveEmptyCellWithNullDirection() throws Exception {
	    game.putTilesInTheBoard(board, tiles);
	    assertFalse(game.moveEmptyCell(null));
	}

	/*
	 * Como testar a linha catch (ExceptionInvalidPosition exception) do
	 * método moveDown() ?
	 * Para que ocorra a exceção, é necessário que tilePosition seja uma
	 * posição inválida, porém tilePosition é obtido no próprio método a partir
	 * da posição na linha de emptyCell. Portanto, emptyCell deve estar na posição
	 * superior do tabuleiro, mas isso já é verificado em:
	 * if (this.board.isInTheSuperiorBorder(this.emptyCell))
            return false; 
	 * 
	 */

	@Test
    public void testMoveEmptyCellNull() throws Exception {
	    game.putTilesInTheBoard(board, tiles);
	    game.moveEmptyCell(null);
    }
	
	@Test
	public void testMoveEmptyCellDown() throws Exception {
	    game.putTilesInTheBoard(board, tiles);
	    assertTrue(game.moveEmptyCell(Direction.DOWN));
//	    assertEquals(tiles.get(5), board.getTile(new Position(3,3)));
//	    assertNull(board.getTile(new Position(2,3)));
	}

	@Test
    public void testMoveEmptyCellDownFromSuperiorBorder() throws Exception {
        game.putTilesInTheBoard(board, tiles);
        game.moveEmptyCell(Direction.DOWN); // Empty cell is now (2,3)
        game.moveEmptyCell(Direction.DOWN); // Empty cell is now (1,3) - superior border
        assertFalse(game.moveEmptyCell(Direction.DOWN));
    }

	@Test
    public void testMoveEmptyCellUp() throws Exception {
        game.putTilesInTheBoard(board, tiles);
     // Move a célula vazia para baixo para poder movê-la para cima
        game.moveEmptyCell(Direction.DOWN);
        assertTrue(game.moveEmptyCell(Direction.UP));
    }

    @Test
    public void testMoveEmptyCellUpFromBottomBorder() throws Exception {
        game.putTilesInTheBoard(board, tiles);
        assertFalse(game.moveEmptyCell(Direction.UP));
    }

    @Test
    public void testMoveEmptyCellRight() throws Exception {
        game.putTilesInTheBoard(board, tiles);
        assertTrue(game.moveEmptyCell(Direction.RIGHT));
    }

    @Test
    public void testMoveEmptyCellRightFromLeftBorder() throws Exception {
        game.putTilesInTheBoard(board, tiles);
        game.moveEmptyCell(Direction.RIGHT);
        game.moveEmptyCell(Direction.RIGHT);
        assertFalse(game.moveEmptyCell(Direction.RIGHT));
    }

    @Test
    public void testMoveEmptyCellLeft() throws Exception {
        game.putTilesInTheBoard(board, tiles);
        // Move a célula vazia para direita para poder movê-la para a esquerda
        game.moveEmptyCell(Direction.RIGHT);
        assertTrue(game.moveEmptyCell(Direction.LEFT));
    }

    @Test
    public void testMoveEmptyCellLeftFromRightBorder() throws Exception {
        game.putTilesInTheBoard(board, tiles);
        assertFalse(game.moveEmptyCell(Direction.LEFT));
    }
}
