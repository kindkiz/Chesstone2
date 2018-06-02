package chess;

import pieces.Knight;
import pieces.Queen;

public class Checker implements ConstDef{
	
	//kindkiz �ۼ�
	
	final int BOARD1MAX = 8;

	public boolean isChecked(Board_1 board,int turn) {
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
		if(turn == WHITE && board.p2_catchable[KingX][KingY])
		{
			return true;
		}
		else if (board.p1_catchable[KingX][KingY]) {
			return true;
		}

		return false;
	}

	public boolean isChecked(Board_2 board,int turn) {
		// find king position

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
		
		// 1. king�� �ѷ��� 8ĭ�� ��� �Ʊ��̰ų� catchable �̸� checkMate
		for (int i = -1; i <= 1; i++) 
		{
			for (int j = -1; j <= 1; j++) 
			{
				if (!(i == 0 && j == 0)) 
				{
					if (((0 <= KingX + i) && (KingX + i < BOARD1MAX)) && ((0 <= KingY + i) && KingY + i < BOARD1MAX))
					{
						if ((board.getPiece(KingX + i, KingY + j) == null) && (turn == WHITE) ? (!board.p2_catchable[KingX + i][KingY + j]) : (!board.p1_catchable[KingX + i][KingY + j]))
						{
							// ���� �Ұ����� ������ ������
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
		Knight kingCloneKn;
	
		Queen kingCloneQu;
		/*for(int i = 0, j = 0;i < BOARD1MAX && j < BOARD1MAX;i++ , j++)
		{
			if(board.getPiece(BKingX, i) != null && board.getPiece(BKingX, i).getColor() != 3)
			{
				count++;
				assassinX = BKingX;
				assassinY = i;
			}
			if(board.getPiece(i, BKingY) != null && board.getPiece(i, BKingY).getColor() != 3)
			{
				count++;
				assassinX = i;
				assassinY = BKingY;
			}
			if(board.getPiece(WKingX, i) != null && board.getPiece(WKingX, i).getColor() != 1)
			{
				count++;
				assassinX = WKingX;
				assassinY = i;
			}
			if(board.getPiece(i, WKingY) != null && board.getPiece(i, WKingY).getColor() != 1)
			{
				count++;
				assassinX = i;
				assassinY = WKingY;
			}
			
			if(count >= 2)
				return true;
		}
	*/
		
		return true;
	}

	public boolean isCheckMate(Board_2 board,int turn) {
		if (!isChecked(board, turn)) {
			return false;
		}

		return false;
	}

	public boolean isStaleMate(Board_1 board,int turn) {
		//��¥ ���״�� �ƹ��͵� ������ ���°� �Ǿ����.
		if (isChecked(board, turn)) {
			return false;
		}
		for(int X = 0;X < BOARD1MAX;X++)
		{
			for(int Y = 0;Y < BOARD1MAX; Y++)
			{
				if((turn == WHITE) ? board.p1_catchable[X][Y] : board.p2_catchable[X][Y])
					return false;
			}
		}
		return true;
	}

	public boolean isStaleMate(Board_2 board,int turn) {
		if (isChecked(board, turn)) {
			return false;
		}

		return false;
	}
}
