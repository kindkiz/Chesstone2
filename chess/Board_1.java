package chess;

import pieces.*;


public class Board_1 implements ConstDef {

	// 20180531 RedJen Initialized

	private Piece[][] board;

	final Piece[][] initialBoard = {
			{ new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK), new King(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK) },
			{ new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK) },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() }, 
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() }, 
			{ new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT(), new MT() },
			{ new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE) },
			{ new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE), new King(WHITE), new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE) },
			};

	public int turn;
	public Piece curPiece;
	public Position curPiecePos;
	public boolean p1_catchable[][] = new boolean[8][8];
	public boolean p2_catchable[][] = new boolean[8][8];

	public Board_1() {
		this.board = initialBoard;
		curPiece = null;
		turn = WHITE;
	}

	//return null if (x, y) is out of range
	//if there is "i" players
	public Piece getPiece(int PieceX, int PieceY) {
		if(Piece.players == 1)
		{
			if(!Position.inRange(PieceX, PieceY)) return null;
		}
		return board[PieceX][PieceY];
	}
	

	public Piece getPiece(Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		if(Piece.players == 1)
		{
			if(!Position.inRange(x, y)) return null;
		}
		return board[x][y];
	}
	
	void Move(Position PiecePosition, Position dest) {
		if(getPiece(PiecePosition) instanceof Pawn) {
			((Pawn) getPiece(PiecePosition)).moved();
		}
		board[dest.getX()][dest.getY()] = board[PiecePosition.getX()][PiecePosition.getY()];
		board[PiecePosition.getX()][PiecePosition.getY()] = new MT();
		System.out.println(board[PiecePosition.getX()][PiecePosition.getY()] + " , " +board[dest.getX()][dest.getY()]);
	}
	
	void nextTurn() {
		turn+=2;
		if(turn > 4)
			turn = 1;
	}
}
	


