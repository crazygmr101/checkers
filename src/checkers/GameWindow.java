/**
 * DO NOT MODIFY THIS FILE
 * This file is currently being worked on in WindowBuilder, and
 * may not parse correctly if modified
 */
package checkers;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7384928133320272861L;
	public JPanel contentPane;
	private JPanel movePanel;
	private JPanel boardPanel;
	private JLabel lblStatus;
	private Board board;
	boolean cont = true;
	private static GameWindow frame;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		EventQueue.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				try {
					frame = new GameWindow();
					setHandlers(frame);
					colorBoard(frame);
					setSizes(frame);
					colorButtons(frame);
					frame.setVisible(true);
					frame.board = new Board();
					frame.update();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		while (frame.cont)
			frame.repaint();
		
	}

	public void update() {
		paint();
	}
	
	@Override
	public void repaint() {
		paint();
	}
	
	@Override
	public void repaint(long time, int x, int y, int x1, int x2) {
		paint();
	}
	
	public void paint() {
		Canvas[][] cnv_a = new Canvas[8][8];
		Component[] components = this.boardPanel.getComponents();
		char[][] ca = board.getBoard();
		int a = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				cnv_a[i][j] = (Canvas)components[a++];
			}
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if ( (j + i ) % 2 == 0)
					continue;
				Graphics g = cnv_a[i][j].getGraphics();
				Color c;
				switch ( ca[i][j] ) {
				case CheckersConstants.BCHEC:
					c = Color.BLACK;
					break;
				case CheckersConstants.BKING:
					c = Color.BLACK;
					break;
				case CheckersConstants.WCHEC:
					c = Color.RED;
					break;
				case CheckersConstants.WKING:
					c = Color.RED;
					break;
				default:
					c = Color.WHITE;
				}
				cnv_a[i][j].setForeground(c);
				g.setColor(c);
				g.fillOval(5, 5, cnv_a[i][j].getWidth() - 10,  cnv_a[i][j].getHeight() - 10);
			}
	}

	private static void colorButtons(GameWindow frame) {
		Component[] components = frame.getMovePanel().getComponents();
		for (Component cmp : components) {
			if (! (cmp instanceof Canvas))
				continue;
			Canvas cnv = (Canvas)cmp;
			cnv.getGraphics().drawString(cnv.getName(), 0, 0);
		}

	}

	private static void setSizes(GameWindow frame) {
		frame.setSize(1000, 500);
		for (Component cnv : frame.movePanel.getComponents()) {
			int height = cnv.getHeight();
			if ( cnv instanceof Canvas )
				((Canvas)cnv).setSize(500, height);
			cnv.setPreferredSize(new Dimension(500, height));
		}
		frame.pack();
	}

	private static void setHandlers(GameWindow frame) {
		for (Component cnv : frame.movePanel.getComponents())
			cnv.addMouseListener(new Handler(frame));
		for (Component cnv : frame.boardPanel.getComponents())
			cnv.addMouseListener(new Handler(frame));
		frame.movePanel.addMouseListener(new Handler(frame));
	}

	private static void colorBoard(GameWindow frame) {
		Component[] components = frame.boardPanel.getComponents();
		for (Component cmp : components) {
			if (! (cmp instanceof Canvas))
				continue;
			Canvas cnv = (Canvas)cmp;
			int s = Integer.parseInt(cnv.getName());
			int r = s % 10;
			int c = (s - r ) / 10;
			if ((r + c) % 2 == 0) {
				cnv.setBackground(Color.BLACK);
			} else {
				cnv.setBackground(Color.WHITE);
			}
		}
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public GameWindow() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setTitle("Checkers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 379);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblStatus = new JLabel("Checkers Game");
		lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblStatus, BorderLayout.NORTH);

		boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(500, 500));
		boardPanel.setSize(new Dimension(500, 500));
		boardPanel.setMaximumSize(new Dimension(500, 500));
		contentPane.add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(new GridLayout(8, 8, 0, 0));

		Canvas cnv_00 = new Canvas();
		cnv_00.setBackground(Color.BLACK);
		boardPanel.add(cnv_00);
		cnv_00.setName("00");

		Canvas cnv_10 = new Canvas();
		boardPanel.add(cnv_10);
		cnv_10.setName("10");

		Canvas cnv_20 = new Canvas();
		boardPanel.add(cnv_20);
		cnv_20.setName("20");

		Canvas cnv_30 = new Canvas();
		boardPanel.add(cnv_30);
		cnv_30.setName("30");

		Canvas cnv_40 = new Canvas();
		boardPanel.add(cnv_40);
		cnv_40.setName("40");

		Canvas cnv_50 = new Canvas();
		boardPanel.add(cnv_50);
		cnv_50.setName("50");

		Canvas cnv_60 = new Canvas();
		boardPanel.add(cnv_60);
		cnv_60.setName("60");

		Canvas cnv_70 = new Canvas();
		boardPanel.add(cnv_70);
		cnv_70.setName("70");

		Canvas cnv_01 = new Canvas();
		boardPanel.add(cnv_01);
		cnv_01.setName("01");

		Canvas cnv_11 = new Canvas();
		boardPanel.add(cnv_11);
		cnv_11.setName("11");

		Canvas cnv_21 = new Canvas();
		boardPanel.add(cnv_21);
		cnv_21.setName("21");

		Canvas cnv_31 = new Canvas();
		boardPanel.add(cnv_31);
		cnv_31.setName("31");

		Canvas cnv_41 = new Canvas();
		boardPanel.add(cnv_41);
		cnv_41.setName("41");

		Canvas cnv_51 = new Canvas();
		boardPanel.add(cnv_51);
		cnv_51.setName("51");

		Canvas cnv_61 = new Canvas();
		boardPanel.add(cnv_61);
		cnv_61.setName("61");

		Canvas cnv_71 = new Canvas();
		boardPanel.add(cnv_71);
		cnv_71.setName("71");

		Canvas cnv_02 = new Canvas();
		boardPanel.add(cnv_02);
		cnv_02.setName("02");

		Canvas cnv_12 = new Canvas();
		boardPanel.add(cnv_12);
		cnv_12.setName("12");

		Canvas cnv_22 = new Canvas();
		boardPanel.add(cnv_22);
		cnv_22.setName("22");

		Canvas cnv_32 = new Canvas();
		boardPanel.add(cnv_32);
		cnv_32.setName("32");

		Canvas cnv_42 = new Canvas();
		boardPanel.add(cnv_42);
		cnv_42.setName("42");

		Canvas cnv_52 = new Canvas();
		boardPanel.add(cnv_52);
		cnv_52.setName("52");

		Canvas cnv_62 = new Canvas();
		boardPanel.add(cnv_62);
		cnv_62.setName("62");

		Canvas cnv_72 = new Canvas();
		boardPanel.add(cnv_72);
		cnv_72.setName("72");

		Canvas cnv_03 = new Canvas();
		boardPanel.add(cnv_03);
		cnv_03.setName("03");

		Canvas cnv_13 = new Canvas();
		boardPanel.add(cnv_13);
		cnv_13.setName("13");

		Canvas cnv_23 = new Canvas();
		boardPanel.add(cnv_23);
		cnv_23.setName("23");

		Canvas cnv_33 = new Canvas();
		boardPanel.add(cnv_33);
		cnv_33.setName("33");

		Canvas cnv_43 = new Canvas();
		boardPanel.add(cnv_43);
		cnv_43.setName("43");

		Canvas cnv_53 = new Canvas();
		boardPanel.add(cnv_53);
		cnv_53.setName("53");

		Canvas cnv_63 = new Canvas();
		boardPanel.add(cnv_63);
		cnv_63.setName("63");

		Canvas cnv_73 = new Canvas();
		boardPanel.add(cnv_73);
		cnv_73.setName("73");

		Canvas cnv_04 = new Canvas();
		boardPanel.add(cnv_04);
		cnv_04.setName("04");

		Canvas cnv_14 = new Canvas();
		boardPanel.add(cnv_14);
		cnv_14.setName("14");

		Canvas cnv_24 = new Canvas();
		boardPanel.add(cnv_24);
		cnv_24.setName("24");

		Canvas cnv_34 = new Canvas();
		boardPanel.add(cnv_34);
		cnv_34.setName("34");

		Canvas cnv_44 = new Canvas();
		boardPanel.add(cnv_44);
		cnv_44.setName("44");

		Canvas cnv_54 = new Canvas();
		boardPanel.add(cnv_54);
		cnv_54.setName("54");

		Canvas cnv_64 = new Canvas();
		boardPanel.add(cnv_64);
		cnv_64.setName("64");

		Canvas cnv_74 = new Canvas();
		boardPanel.add(cnv_74);
		cnv_74.setName("74");

		Canvas cnv_05 = new Canvas();
		boardPanel.add(cnv_05);
		cnv_05.setName("05");

		Canvas cnv_15 = new Canvas();
		boardPanel.add(cnv_15);
		cnv_15.setName("15");

		Canvas cnv_25 = new Canvas();
		boardPanel.add(cnv_25);
		cnv_25.setName("25");

		Canvas cnv_35 = new Canvas();
		boardPanel.add(cnv_35);
		cnv_35.setName("35");

		Canvas cnv_45 = new Canvas();
		boardPanel.add(cnv_45);
		cnv_45.setName("45");

		Canvas cnv_55 = new Canvas();
		boardPanel.add(cnv_55);
		cnv_55.setName("55");

		Canvas cnv_65 = new Canvas();
		boardPanel.add(cnv_65);
		cnv_65.setName("65");

		Canvas cnv_75 = new Canvas();
		boardPanel.add(cnv_75);
		cnv_75.setName("75");

		Canvas cnv_06 = new Canvas();
		boardPanel.add(cnv_06);
		cnv_06.setName("06");

		Canvas cnv_16 = new Canvas();
		boardPanel.add(cnv_16);
		cnv_16.setName("16");

		Canvas cnv_26 = new Canvas();
		boardPanel.add(cnv_26);
		cnv_26.setName("26");

		Canvas cnv_36 = new Canvas();
		boardPanel.add(cnv_36);
		cnv_36.setName("36");

		Canvas cnv_46 = new Canvas();
		boardPanel.add(cnv_46);
		cnv_46.setName("46");

		Canvas cnv_56 = new Canvas();
		boardPanel.add(cnv_56);
		cnv_56.setName("56");

		Canvas cnv_66 = new Canvas();
		boardPanel.add(cnv_66);
		cnv_66.setName("66");

		Canvas cnv_76 = new Canvas();
		boardPanel.add(cnv_76);
		cnv_76.setName("76");

		Canvas cnv_07 = new Canvas();
		boardPanel.add(cnv_07);
		cnv_07.setName("07");

		Canvas cnv_17 = new Canvas();
		boardPanel.add(cnv_17);
		cnv_17.setName("17");

		Canvas cnv_27 = new Canvas();
		boardPanel.add(cnv_27);
		cnv_27.setName("27");

		Canvas cnv_37 = new Canvas();
		boardPanel.add(cnv_37);
		cnv_37.setName("37");

		Canvas cnv_47 = new Canvas();
		boardPanel.add(cnv_47);
		cnv_47.setName("47");

		Canvas cnv_57 = new Canvas();
		boardPanel.add(cnv_57);
		cnv_57.setName("57");

		Canvas cnv_67 = new Canvas();
		boardPanel.add(cnv_67);
		cnv_67.setName("67");

		Canvas cnv_77 = new Canvas();
		boardPanel.add(cnv_77);
		cnv_77.setName("77");

		movePanel = new JPanel();
		movePanel.setMinimumSize(new Dimension(250, 10));
		contentPane.add(movePanel, BorderLayout.EAST);
		movePanel.setLayout(new GridLayout(10, 1, 0, 0));
		movePanel.setName("movePnl");

		JButton cnvMove_1 = new JButton("1");
		cnvMove_1.setName("1");
		movePanel.add(cnvMove_1);

		JButton cnvMove_2 = new JButton("2");
		cnvMove_2.setName("2");
		movePanel.add(cnvMove_2);

		JButton cnvMove_3 = new JButton("3");
		cnvMove_3.setName("3");
		movePanel.add(cnvMove_3);

		JButton cnvMove_4 = new JButton("4");
		cnvMove_4.setName("4");
		movePanel.add(cnvMove_4);

		JButton cnvMove_5 = new JButton("5");
		cnvMove_5.setName("5");
		movePanel.add(cnvMove_5);

		JButton cnvMove_6 = new JButton("6");
		cnvMove_6.setName("6");
		movePanel.add(cnvMove_6);

		JButton cnvMove_7 = new JButton("7");
		cnvMove_7.setName("7");
		movePanel.add(cnvMove_7);

		JButton cnvMove_8 = new JButton("8");
		cnvMove_8.setName("8");
		movePanel.add(cnvMove_8);
	}

	public JPanel getMovePanel() {
		return movePanel;
	}
	public JPanel getBoardPanel() {
		return boardPanel;
	}
	public JLabel getLblStatus() {
		return lblStatus;
	}
}

class Handler implements MouseListener {

	private GameWindow win;

	public Handler(GameWindow win) { this.win = win; }

	@Override
	public void mouseClicked(MouseEvent e) {
		win.getLblStatus().setText(e.getComponent().getName());
		if (e.getComponent().getName() == "movePnl")
			win.cont = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getComponent() instanceof Canvas) {
			Canvas s = (Canvas) e.getComponent();
			s.getGraphics().setColor(Color.LIGHT_GRAY);
			s.getGraphics().drawRect(5, 5, s.getWidth() - 10, s.getHeight() - 10);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getComponent() instanceof Canvas) {
			Canvas s = (Canvas) e.getComponent();
			s.getGraphics().setColor(Color.LIGHT_GRAY);
			s.getGraphics().drawRect(5, 5, s.getWidth() - 10, s.getHeight() - 10);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		win.getLblStatus().setText(e.getComponent().getName());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
