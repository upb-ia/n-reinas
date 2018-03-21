package edu.upb.n_reinas;

import java.util.ArrayList;
import java.util.List;

public class NQueensState {

    private char[][] board;
    // convention
    // 'R' = Reina
    // ' ' = vacio
    // 'X' = bloqueado
    private int size;

    public NQueensState( int n ) {
        size = n;
        board = new char[ n ][ n ];
    }

    public NQueensState( int n, char[][] board ) {
        this.size = n;
        this.board = board;
    }

    List< NQueensState > children;

    void expandChildren() {
        children = new ArrayList<>();
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                if ( board[ i ][ j ] == ' ' ) {
                    char[][] childBoard = cloneBoard();
                    childBoard[ i ][ j ] = 'R';
                    blockRowAndColumn( childBoard, i, j );
                    blockDiagonals( childBoard, i, j );
                    children.add( new NQueensState( size, childBoard ) );
                }
            }
        }
    }

    private char[][] cloneBoard() {
        char[][] result = new char[ size ][ size ];
        for ( int i = 0; i < 8; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                result[ i ][ j ] = board[ i ][ j ];
            }
        }
        return result;
    }


    private void blockDiagonals( char[][] board, int i, int j ) {
        for ( int b = 1; b < size; b++ ) {
            if ( i - b >= 0 && j - b >= 0 ) {
                board[ i - b ][ j - b ] = 'X';
            }
            if ( i + b < size && j + b < size ) {
                board[ i + b ][ j + b ] = 'X';
            }
            if ( i - b >= 0 && j + b < size ) {
                board[ i - b ][ j + b ] = 'X';
            }
            if ( i + b < size && j - b >= 0 ) {
                board[ i + b ][ j - b ] = 'X';
            }
        }
    }

    private void blockRowAndColumn( char[][] board, int i, int j ) {
        for ( int b = 0; b < 8; b++ ) {
            // todas las columnas menos la actual
            if ( b != j ) {
                board[ i ][ b ] = 'X';
            }
            if ( b != i ) {
                board[ b ][ j ] = 'X';
            }
        }
    }

    boolean isGoalState() {
        // hay N reinas?
        int numQueens = 0;
        for ( int i = 0; i < 8; i++ ) {
            for ( int j = 0; j < 8; j++ ) {
                if ( board[ i ][ j ] == 'R' ) {
                    numQueens++;
                }
            }
        }
        return numQueens == 8;
    }

    void printBoard() {
        for ( int i = 0; i < 8; i++ ) {
            for ( int j = 0; j < 8; j++ ) {
                System.out.print( board[ i ][ j ] );
            }
            System.out.println();
        }
    }
}
