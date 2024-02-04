import org.junit.Assert;
import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class QueensAttack {

    static class Coordinate{

        public int x;
        public int y;

        public Coordinate(int _x, int _y) {
            x=_x;
            y=_y;
        }
    }

    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */
    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
    List<Function<Coordinate,Coordinate>> QueenMovements = new ArrayList<>();
    Collections.addAll(QueenMovements, moveDiagonallyRightUp,moveDiagonallyLeftDown,moveDiagonallyLeftUp ,moveDiagonallyRightDown, moveRowUp,moveRowDown,moveColUp,moveColDown);
    Coordinate queenPosition = new Coordinate(r_q , c_q);

    int possibleQueenAttackSq = 0;

        for (Function<Coordinate,Coordinate> qm: QueenMovements) {
            Coordinate nextMove = qm.apply(queenPosition);
            while(!isOutsideBoard.apply (nextMove,n)
                    && !anObstacle(obstacles ,nextMove )){
                nextMove = qm.apply(nextMove);
                possibleQueenAttackSq++;
            }
        }
        return possibleQueenAttackSq;
    }

    private static boolean anObstacle(List<List<Integer>> obstacles, Coordinate queenPos) {
        List<Integer> ints = new ArrayList<>();
        Collections.addAll(ints , queenPos.x, queenPos.y);
        //if(obstacles == null) return false;
        return null != obstacles  && obstacles.contains(ints);
    }

    static Function<Coordinate,Coordinate> moveDiagonallyRightUp = c -> new Coordinate(c.x+1, c.y+1);
    static Function<Coordinate,Coordinate> moveDiagonallyLeftDown = c -> new Coordinate(c.x-1, c.y-1);


    static Function<Coordinate,Coordinate> moveDiagonallyLeftUp = c -> new Coordinate(c.x-1, c.y+1);
    static Function<Coordinate,Coordinate> moveDiagonallyRightDown = c -> new Coordinate(c.x+1, c.y-1);

    static Function<Coordinate,Coordinate> moveRowUp = c -> new Coordinate(c.x+1, c.y);
    static Function<Coordinate,Coordinate> moveRowDown = c -> new Coordinate(c.x-1, c.y);
    static Function<Coordinate,Coordinate> moveColUp = c -> new Coordinate(c.x, c.y+1);
    static Function<Coordinate,Coordinate> moveColDown = c -> new Coordinate(c.x, c.y-1);

    static BiFunction<Coordinate,Integer, Boolean> isOutsideBoard = (c,n) ->  n<c.x || n< c.y  || 1 > c.x || 1 > c.y;


    @Test
    public void test4044ex(){

        int attacks = queensAttack (4,0,4,4, null);
        Assert.assertEquals(9,attacks);
    }

    @Test
    public void testNextex(){
        List<List<Integer>> obstacles = new ArrayList<>();
        Collections.addAll(obstacles, Arrays.asList(5,5) ,Arrays.asList(4,2),Arrays.asList(2,3) );
        int attacks = queensAttack (5,3,4,3, obstacles);
        Assert.assertEquals(10,attacks);
    }


}
