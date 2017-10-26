import java.util.ArrayList;

public class Maze {
    public static void main(String args[]) {
        Solution solution = new Solution();
        int[][] maze = {{1, 0, 0, 0},
                        {1, 1, 1, 1},
                        {0, 1, 1, 1},
                        {1, 0, 0, 1}};
        //int[][] maze = {{1,1},{0,1}};
        ArrayList<Cell> cells = solution.findPath(maze);

        System.out.println("result: ");
        for (Cell cell : cells) {
            System.out.print(cell + " ");
        }
    }
}

class Cell{
    int x,y;
    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "["+this.x +", "+this.y+ "]";
    }

}
class Solution{
    private static final int DEAD_END = 0;
    private static final int PATH = 1;
    public ArrayList<Cell> findPath(int[][] maze) {
        //your code
        ArrayList<Cell> path = new ArrayList<>();
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return path;
        }
        int n = maze.length;
        int m = maze[0].length;
        if (maze[0][0] == DEAD_END || maze[n - 1][m - 1] == DEAD_END) {
            return path;
        }

        Cell startCell = new Cell(0, 0);
        helper(maze, path, startCell);
        return path;
    }
    private boolean helper(int[][] maze, ArrayList<Cell> path, Cell curentCell) {
        int[] directionX = {1, 0};
        int[] directionY = {0, 1};

        int n = maze.length;
        int m = maze[0].length;

        if (curentCell.x == n - 1 && curentCell.y == m - 1) {
            path.add(curentCell);
            return true;
        }

        for (int i = 0; i < 2; i++) {
            if (!isInBoundary(curentCell, n, m)) {
                continue;
            }
            if (maze[curentCell.x][curentCell.y] == DEAD_END) {
                continue;
            }

            Cell nextDirect = new Cell(curentCell.x + directionX[i], curentCell.y + directionY[i]);
            path.add(curentCell);
            if (helper(maze, path, nextDirect)) {
                return true;
            }

            path.remove(path.size() - 1);
        }

        return false;
    }

    private boolean isInBoundary(Cell nextDirect, int n, int m) {
        return (nextDirect.x >= 0 && nextDirect.x < n && nextDirect.y >= 0 && nextDirect.y < m);
    }
}
