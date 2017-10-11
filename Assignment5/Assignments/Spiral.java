import java.util.ArrayList;
import java.util.List;

public class Spiral {
	private static final int DOWN = 0;
	private static final int RIGHT = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;

	private static final int[] dx = {1, 0, 0, -1};
	private static final int[] dy = {0, 1, -1, 0};

	public int turn(int direction) {
		if (direction == RIGHT) {
			return DOWN;
		} else if (direction == DOWN) {
			return LEFT;
		} else if (direction == LEFT) {
			return UP;
		} else if (direction == UP) {
			return RIGHT;
		}
		return -1;
	}

	public int[] move(int[] curMove, int direction) {
		int[] nextMove = new int[2];
		nextMove[0] = curMove[0] + dx[direction];
		nextMove[1] = curMove[1] + dy[direction];
		return nextMove;
	}
//
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return result;
		}
		if (matrix[0] == null || matrix[0].length == 0) {
			return result;
		}

		int m = matrix.length;
		if (m == 1) {
			for (int i = 0; i < matrix[0].length; i++) {
				result.add(matrix[0][i]);
			}
			return result;
		}
		int n = matrix[0].length;
		if(n == 1) {
			for (int i = 0; i < matrix.length; i++) {
				result.add(matrix[i][0]);
			}
			return result;
		}

		int direction = RIGHT;
		int[] curMove = {0, 0};

		for (int i = 0; i < m * n; i++) {
			result.add(matrix[curMove[0]][curMove[1]]);
			matrix[curMove[0]][curMove[1]] = -1;
			int[] nextMove = move(curMove, direction);

			if (nextMove[0] < 0 || nextMove[0] >= m
				||nextMove[1] < 0 || nextMove[1] >= n
				|| matrix[nextMove[0]][nextMove[1]] == -1) {
				direction = turn(direction);
				nextMove = move(curMove, direction);
			}
			curMove = nextMove;
		}
		return result;
	}
}