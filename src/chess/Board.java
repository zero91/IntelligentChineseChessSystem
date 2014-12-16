package chess;

import java.util.Map;

/**
 * Created by Tong on 12.03.
 * Chess > Board entity
 */


public class Board {
    public final int BOARD_WIDTH = 9, BOARD_HEIGHT = 10;
    private Piece[][] cells = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
    public Map<String, Piece> pieces;
    public char player = 'r';

    public boolean isInside(int[] position) {
        return isInside(position[0], position[1]);
    }

    public boolean isInside(int x, int y) {
        return !(x < 0 || x >= BOARD_HEIGHT
                || y < 0 || y >= BOARD_WIDTH);
    }

    public boolean isEmpty(int[] position) {
        return isEmpty(position[0], position[1]);
    }

    public boolean isEmpty(int x, int y) {
        return isInside(x, y) && cells[x][y] == null;
    }


    public boolean update(Piece piece) {
        int[] pos = piece.position;
        cells[pos[0]][pos[1]] = piece;
        return true;
    }

    public boolean updatePiece(String key, int[] newPos) {
        Piece orig = pieces.get(key);
        Piece inNewPos = getPiece(newPos);
        if (inNewPos != null) {
            /* If the new slot has been taken by another piece, then it will be killed.*/
            pieces.remove(inNewPos.key);
        }
        /* Clear original slot and updatePiece new slot.*/
        int[] origPos = orig.position;
        cells[origPos[0]][origPos[1]] = null;
        cells[newPos[0]][newPos[1]] = orig;
        orig.position = newPos;
        player = (player == 'r') ? 'b' : 'r';
        return true;
    }

    public Piece getPiece(int[] pos) {
        return cells[pos[0]][pos[1]];
    }

    public Piece getPiece(int x, int y) {
        return cells[x][y];
    }

}