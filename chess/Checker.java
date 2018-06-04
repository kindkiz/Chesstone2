package chess;

import java.util.ArrayList;
import java.util.Iterator;

import pieces.Bishop;
import pieces.Knight;
import pieces.Position;
import pieces.Rook;

public class Checker implements ConstDef {

	// kindkiz �ۼ�

	final int BOARD1MAX = 8;
	final int BOARD2MAX = 14;

	public boolean isChecked(Board_1 board, int turn) {
		// find king position
		int KingX = 0, KingY = 0;

		for (int X = 0; X < BOARD1MAX; X++) {
			for (int Y = 0; Y < BOARD1MAX; Y++) {
				if (board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == turn) {
					KingX = X;
					KingY = Y;
				}
			}
		}

		// done
		if (turn == WHITE && board.getCatchable(BLACK)[KingX][KingY]) {
			return true;
		} else if (board.getCatchable(WHITE)[KingX][KingY]) {
			return true;
		}

		return false;
	}

	public boolean isChecked(Board_2 board, int turn) {
		// find king position
		int KingX = 0, KingY = 0;
		 
		for (int X = 0; X < BOARD2MAX; X++) {
			for (int Y = 0; Y < BOARD2MAX; Y++) {
				if(board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == turn)
				{
					KingX = X;
					KingY = Y;
				}
			}
		}
		// done
		if((turn == WHITE || turn == BLACK) && board.p2_catchable[KingX][KingY])
		{
			return true;
		}
		else if (board.p1_catchable[KingX][KingY]) {
			return true;
		}
		return false;
	}

	public boolean isCheckMate(Board_1 board,int turn) {
		if (!isChecked(board, turn)) {
			return false;
		}
		// find king position
		int KingX = 0, KingY = 0;

		for (int X = 0; X < BOARD1MAX; X++) {
			for (int Y = 0; Y < BOARD1MAX; Y++) {
				if(board.getPiece(X, Y).getName() == KING && board.getPiece(X, Y).getColor() == turn)
				{
					KingX = X;
					KingY = Y;
				}
			}
		}
		// done
		
		// king�� �ѷ��� 8ĭ�� ��� �Ʊ��̰ų� catchable �̸� ���� �̵��� �Ұ��ϴ�.
		for (int i = -1; i <= 1; i++) 
		{
			for (int j = -1; j <= 1; j++) 
			{
				if (!(i == 0 && j == 0)) 
				{
					if (((0 <= KingX + i) && (KingX + i < BOARD1MAX)) && ((0 <= KingY + i) && KingY + i < BOARD1MAX))
					{
						if ((board.getPiece(KingX + i, KingY + j) == null && (turn == WHITE) ? (!board.getCatchable(BLACK)[KingX + i][KingY + j]) : (!board.getCatchable(WHITE)[KingX + i][KingY + j])))
						{
							// ��밡 ���� �Ұ����� ������ ������
							return false;
						}
						else if (board.getPiece(KingX + i, KingY + j).getColor() != turn) {
							// �����̸�
							return false;
						}
					}
				}
			}
		}
		// ����ó�� : king�� ���� ��θ� ������ �� �ִ°�?
		int count = 0; //���� ������ �� �ִ� �⹰�� 2���� �Ǵ� ���� checkMate!
		//isChecked�� true ���� ������ �ϻ��ڴ� ������ �Ѹ���. 
		int assassinX = 0,assassinY = 0;
		
		Knight kingCloneKn = new Knight(10);
		Rook kingCloneRo = new Rook(10);
		Bishop kingCloneBi = new Bishop(10);
		//���ݴ��� �� �ִ� ��ġ�� ������ �� �ִ� �⹰�� �ֳ� üũ ���� �ΰ� �̻��̸� �ٷ� return true.
		Iterator<Position> knitr = kingCloneKn.getMovement(board, new Position(KingX,KingY)).iterator();
		for(;knitr.hasNext();)
		{
			if(board.getPiece(knitr.next().getX(), knitr.next().getY()) != null && (board.getPiece(knitr.next().getX(), knitr.next().getY()).getColor() != turn && board.getPiece(knitr.next().getX(), knitr.next().getY()).getName() == KNIGHT))
			{
				count++;
				assassinX = knitr.next().getX();
				assassinY = knitr.next().getY();
			}
		}
		if(count >= 2)
			return true;
		Iterator<Position> Roitr = kingCloneRo.getMovement(board, new Position(KingX,KingY)).iterator();
		for(;Roitr.hasNext();)
		{
			if(board.getPiece(Roitr.next().getX(), Roitr.next().getY()) != null && (board.getPiece(Roitr.next().getX(), Roitr.next().getY()).getColor() != turn && (board.getPiece(Roitr.next().getX(), Roitr.next().getY()).getName() == ROOK || board.getPiece(Roitr.next().getX(), Roitr.next().getY()).getName() == QUEEN)))
			{
				count++;
				assassinX = Roitr.next().getX();
				assassinY = Roitr.next().getY();
			}
		}
		if(count >= 2)
			return true;
		Iterator<Position> Biitr = kingCloneBi.getMovement(board, new Position(KingX,KingY)).iterator();
		for(;Biitr.hasNext();)
		{
			if(board.getPiece(Biitr.next().getX(), Biitr.next().getY()) != null && (board.getPiece(Biitr.next().getX(), Biitr.next().getY()).getColor() != turn && (board.getPiece(Biitr.next().getX(), Biitr.next().getY()).getName() == BISHOP || board.getPiece(Biitr.next().getX(), Biitr.next().getY()).getName() == QUEEN)))
			{
				count++;
				assassinX = Biitr.next().getX();
				assassinY = Biitr.next().getY();
			}
		}
		if(count >= 2)
			return true;
		
		//���� �� �������� üũ
		if(turn == WHITE && board.getCatchable(WHITE)[assassinX][assassinY])
		{
			return false;
		}
		else if (board.getCatchable(BLACK)[assassinX][assassinY])
		{
			return false;
		}
		//���� �� �ȵȴٸ� checkMate!!
		
		return true;
	}

	public boolean isCheckMate(Board_2 board, int turn) {
		if (!isChecked(board, turn)) {
			return false;
		}

		return false;
	}

	public boolean isStaleMate(Board_1 board, int turn) {
		// ��¥ ���״�� �ƹ��͵� ������ ���°� �Ǿ����.
		if (isChecked(board, turn)) {
			return false;
		}
		for (int X = 0; X < BOARD1MAX; X++) {
			for (int Y = 0; Y < BOARD1MAX; Y++) {
				if ((turn == WHITE) ? board.getCatchable(WHITE)[X][Y] : board.getCatchable(BLACK)[X][Y])
					return false;
			}
		}
		return true;
	}

	public boolean isStaleMate(Board_2 board, int turn) {
		if (isChecked(board, turn)) {
			return false;
		}
		for (int X = 0; X < BOARD2MAX; X++) {
			for (int Y = 0; Y < BOARD2MAX; Y++) {
				if ((turn == WHITE || turn == BLACK) ? board.p1_catchable[X][Y] : board.p2_catchable[X][Y])
					return false;
			}
		}
		return true;
	}
}
