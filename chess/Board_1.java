package chess;

import java.util.ArrayList;
import java.util.Arrays;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.MT;
import pieces.Pawn;
import pieces.Piece;
import pieces.Position;
import pieces.Queen;
import pieces.Rook;

public class Board_1 extends Board_Master implements ConstDef {

	// 20180531 RedJen Initialized

	
	final Piece[][] initialBoard = {
			{ new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK), new King(BLACK),
					new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK) },
			{ new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK),
					new Pawn(BLACK), new Pawn(BLACK) },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE),
					new Pawn(WHITE), new Pawn(WHITE) },
			{ new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE), new King(WHITE),
					new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE) }, };

	boolean[][] getCatchable(int color) {
		boolean[][] catchable = new boolean[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getPiece(i, j).getColor() == color) {
					ArrayList<Position> moveable = getPiece(i, j).getMovement(this, new Position(i, j));
					for (int k = 0; k < moveable.size(); k++) {
						catchable[moveable.get(k).getX()][moveable.get(k).getY()] = true;
					}
				}
			}
		}
		return catchable;
	}

	public Board_1() {
		this.board = initialBoard;
		curPiece = null;
		skipTurn = 2;
		turn = WHITE;
	}

	void Move(Position PiecePosition, Position dest) {
		if (getPiece(PiecePosition) instanceof Pawn) {
			((Pawn) getPiece(PiecePosition)).moved();
		}
		board[dest.getX()][dest.getY()] = board[PiecePosition.getX()][PiecePosition.getY()];
		board[PiecePosition.getX()][PiecePosition.getY()] = new MT();
		curPiece = null;
		nextTurn();
		System.out.println(board[PiecePosition.getX()][PiecePosition.getY()] + " , " + board[dest.getX()][dest.getY()]);

		Checker checker = new Checker();
		if (checker.isChecked(this, turn)) {
			System.out.println("Check");
			if (checker.isCheckMate(this, turn)) {
				System.out.println("Checkmate");
			}

		} else if (checker.isStaleMate(this, turn)) {
			System.out.println("StaleMate");
		}

	}

	// return null if (x, y) is out of range
	// if there is "i" players

}