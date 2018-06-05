package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class GameFrame_2vs2 {
	private static final long serialVersionUID = 1L;

	final int width = 840;
	final int height = 840;
	JFrame gameFrame;
	Board_2 board;
	JPanel[][] square = new JPanel[14][14];
	JPanel chessBoard;
	JPanel playSpectator;
	JTextArea logTextScreen;
	JTextPane turnScreen;
	StyledDocument doc;
	ImagePanel[][] imgPan = new ImagePanel[14][14];

	Font myfont = new Font("NanumGothic", Font.BOLD, 12);
	
	public GameFrame_2vs2() {
		gameFrame = new JFrame("Chess - 2vs2");
		gameFrame.setSize(width, height);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setLayout(new BorderLayout());

		playSpectator = new JPanel();
		playSpectator.setLayout(new BoxLayout(playSpectator, BoxLayout.Y_AXIS));
		
		logTextScreen = new JTextArea(5, 10);
		logTextScreen.setFont(myfont);
		JScrollPane textScrollPane = new JScrollPane(logTextScreen);
		textScrollPane.setPreferredSize(new Dimension(150,500));
		logTextScreen.append("게임 시작!!\n"); //초기 서순
		playSpectator.add(textScrollPane);

		doc = (StyledDocument) new DefaultStyledDocument();
		turnScreen = new JTextPane(doc);
		javax.swing.text.Style textStyle = turnScreen.addStyle("Red", null);
		StyleConstants.setForeground(textStyle, Color.RED);
		turnScreen.setEditable(false);
		turnScreen.setPreferredSize(new Dimension(150,50));
		turnScreen.setText("WHITE Team의 차례!");
		
		playSpectator.add(turnScreen);
		gameFrame.add(playSpectator, BorderLayout.EAST);
		playSpectator.setVisible(true);
		
		JPanel chessBoard = new JPanel();
		chessBoard.setLayout(new GridLayout(14, 14));

		board = new Board_2();

		boolean painter = true;
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				square[i][j] = new JPanel();
				square[i][j].putClientProperty("column", i);
				square[i][j].putClientProperty("row", j);

				if ((0 <= i && i < 3) && (0 <= j && j < 3) || (11 <= i && i < 14) && (0 <= j && j < 3) || (0 <= i && i < 3) && (11 <= j && j < 14) || (11 <= i && i < 14) && (11 <= j && j < 14)) {
					square[i][j].putClientProperty("OutOfBound", true);
				} else
					square[i][j].putClientProperty("OutOfBound", false);
				

				if ((boolean) square[i][j].getClientProperty("OutOfBound") == true)
					square[i][j].setBackground(Color.GRAY);
				else {
					if (painter) {
						square[i][j].setBackground(Color.ORANGE);
						painter = false;
					} else {
						square[i][j].setBackground(Color.YELLOW);
						painter = true;
					}
					//square[i][j].addMouseListener(new MouseEventHandler(board,this));
				}
				chessBoard.add(square[i][j]);
			}
			painter = painter ? false : true;
		}

		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				try {
					imgPan[i][j] = new ImagePanel();
					imgPan[i][j].setImage(board.getPiece(i, j).getImg());
					imgPan[i][j].setPreferredSize(new Dimension(60, 60));
					square[i][j].add(imgPan[i][j]);

				} catch (NullPointerException err) {
				}
			}
		}

		gameFrame.add(chessBoard, BorderLayout.CENTER);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);

	}

}
