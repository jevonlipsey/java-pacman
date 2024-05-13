import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IsWallTest {
	private Map map;

	@BeforeEach
	public void setUp() {
		map = new Map();
		
	}
	
	/**
	 * Test the top wall (h value)
	 */
	@Test
	void testTopWall() {
		assertEquals(true, map.isWall(1, 0));
	}
	
	/**
	 * Test the left wall (v value)
	 */
	@Test
	void testBottomWall() {
		assertEquals(true, map.isWall(0, 1));
	}
	
	/**
	 * Test a wall near the top left represented by a 1
	 */
	@Test
	void test1() {
		assertEquals(true, map.isWall(4, 2));
	}
	
	/**
	 * Test a wall near the top left represented by a 2
	 */
	@Test
	void test2() {
		assertEquals(true, map.isWall(2, 2));
	}
	
	/**
	 * Test a wall near the top left represented by a 3
	 */
	@Test
	void test3() {
		assertEquals(true, map.isWall(4, 9));
	}
	
	/**
	 * Test a wall on the left represented by a 4
	 */
	@Test
	void test4() {
		assertEquals(true, map.isWall(2, 3));
	}
	
	/**
	 * Test a wall near the bottom represented by a U
	 */
	@Test
	void testU() {
		assertEquals(true, map.isWall(6, 23));
	}
	
	/**
	 * Test a wall near the middle represented by a D
	 */
	@Test
	void testD() {
		assertEquals(true, map.isWall(10, 9));
	}
	
	/**
	 * Test a wall near the bottom represented by a L
	 */
	@Test
	void testL() {
		assertEquals(true, map.isWall(14, 10));
	}
	
	/**
	 * Test a wall near the bottom represented by a R
	 */
	@Test
	void testR() {
		assertEquals(true, map.isWall(0, 17));
	}
	
	/**
	 * Tests part of the track, represented by a p
	 */
	@Test
	void testOpenSpace() {
		assertEquals(false, map.isWall(1, 1));
	}
	
	

}
