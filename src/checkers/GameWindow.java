/**
 * DO NOT MODIFY THIS FILE
 * This file is currently being worked on in WindowBuilder, and
 * may not parse correctly if modified
 */
package checkers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Dimension;

public class GameWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblStatus = new JLabel("Checkers Game");
		lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblStatus, BorderLayout.NORTH);
		
		JPanel boardPanel = new JPanel();
		boardPanel.setSize(new Dimension(500, 500));
		boardPanel.setMaximumSize(new Dimension(500, 500));
		contentPane.add(boardPanel, BorderLayout.CENTER);
		boardPanel.setLayout(new GridLayout(8, 8, 0, 0));
		
		JButton btn_00 = new JButton("00");
		boardPanel.add(btn_00);
		btn_00.setName("00");

		JButton btn_10 = new JButton("10");
		boardPanel.add(btn_10);
		btn_10.setName("10");

		JButton btn_20 = new JButton("20");
		boardPanel.add(btn_20);
		btn_20.setName("20");

		JButton btn_30 = new JButton("30");
		boardPanel.add(btn_30);
		btn_30.setName("30");

		JButton btn_40 = new JButton("40");
		boardPanel.add(btn_40);
		btn_40.setName("40");

		JButton btn_50 = new JButton("50");
		boardPanel.add(btn_50);
		btn_50.setName("50");

		JButton btn_60 = new JButton("60");
		boardPanel.add(btn_60);
		btn_60.setName("60");

		JButton btn_70 = new JButton("70");
		boardPanel.add(btn_70);
		btn_70.setName("70");

		JButton btn_01 = new JButton("01");
		boardPanel.add(btn_01);
		btn_01.setName("01");

		JButton btn_11 = new JButton("11");
		boardPanel.add(btn_11);
		btn_11.setName("11");

		JButton btn_21 = new JButton("21");
		boardPanel.add(btn_21);
		btn_21.setName("21");

		JButton btn_31 = new JButton("31");
		boardPanel.add(btn_31);
		btn_31.setName("31");

		JButton btn_41 = new JButton("41");
		boardPanel.add(btn_41);
		btn_41.setName("41");

		JButton btn_51 = new JButton("51");
		boardPanel.add(btn_51);
		btn_51.setName("51");

		JButton btn_61 = new JButton("61");
		boardPanel.add(btn_61);
		btn_61.setName("61");

		JButton btn_71 = new JButton("71");
		boardPanel.add(btn_71);
		btn_71.setName("71");

		JButton btn_02 = new JButton("02");
		boardPanel.add(btn_02);
		btn_02.setName("02");

		JButton btn_12 = new JButton("12");
		boardPanel.add(btn_12);
		btn_12.setName("12");

		JButton btn_22 = new JButton("22");
		boardPanel.add(btn_22);
		btn_22.setName("22");

		JButton btn_32 = new JButton("32");
		boardPanel.add(btn_32);
		btn_32.setName("32");

		JButton btn_42 = new JButton("42");
		boardPanel.add(btn_42);
		btn_42.setName("42");

		JButton btn_52 = new JButton("52");
		boardPanel.add(btn_52);
		btn_52.setName("52");

		JButton btn_62 = new JButton("62");
		boardPanel.add(btn_62);
		btn_62.setName("62");

		JButton btn_72 = new JButton("72");
		boardPanel.add(btn_72);
		btn_72.setName("72");

		JButton btn_03 = new JButton("03");
		boardPanel.add(btn_03);
		btn_03.setName("03");

		JButton btn_13 = new JButton("13");
		boardPanel.add(btn_13);
		btn_13.setName("13");

		JButton btn_23 = new JButton("23");
		boardPanel.add(btn_23);
		btn_23.setName("23");

		JButton btn_33 = new JButton("33");
		boardPanel.add(btn_33);
		btn_33.setName("33");

		JButton btn_43 = new JButton("43");
		boardPanel.add(btn_43);
		btn_43.setName("43");

		JButton btn_53 = new JButton("53");
		boardPanel.add(btn_53);
		btn_53.setName("53");

		JButton btn_63 = new JButton("63");
		boardPanel.add(btn_63);
		btn_63.setName("63");

		JButton btn_73 = new JButton("73");
		boardPanel.add(btn_73);
		btn_73.setName("73");

		JButton btn_04 = new JButton("04");
		boardPanel.add(btn_04);
		btn_04.setName("04");

		JButton btn_14 = new JButton("14");
		boardPanel.add(btn_14);
		btn_14.setName("14");

		JButton btn_24 = new JButton("24");
		boardPanel.add(btn_24);
		btn_24.setName("24");

		JButton btn_34 = new JButton("34");
		boardPanel.add(btn_34);
		btn_34.setName("34");

		JButton btn_44 = new JButton("44");
		boardPanel.add(btn_44);
		btn_44.setName("44");

		JButton btn_54 = new JButton("54");
		boardPanel.add(btn_54);
		btn_54.setName("54");

		JButton btn_64 = new JButton("64");
		boardPanel.add(btn_64);
		btn_64.setName("64");

		JButton btn_74 = new JButton("74");
		boardPanel.add(btn_74);
		btn_74.setName("74");

		JButton btn_05 = new JButton("05");
		boardPanel.add(btn_05);
		btn_05.setName("05");

		JButton btn_15 = new JButton("15");
		boardPanel.add(btn_15);
		btn_15.setName("15");

		JButton btn_25 = new JButton("25");
		boardPanel.add(btn_25);
		btn_25.setName("25");

		JButton btn_35 = new JButton("35");
		boardPanel.add(btn_35);
		btn_35.setName("35");

		JButton btn_45 = new JButton("45");
		boardPanel.add(btn_45);
		btn_45.setName("45");

		JButton btn_55 = new JButton("55");
		boardPanel.add(btn_55);
		btn_55.setName("55");

		JButton btn_65 = new JButton("65");
		boardPanel.add(btn_65);
		btn_65.setName("65");

		JButton btn_75 = new JButton("75");
		boardPanel.add(btn_75);
		btn_75.setName("75");

		JButton btn_06 = new JButton("06");
		boardPanel.add(btn_06);
		btn_06.setName("06");

		JButton btn_16 = new JButton("16");
		boardPanel.add(btn_16);
		btn_16.setName("16");

		JButton btn_26 = new JButton("26");
		boardPanel.add(btn_26);
		btn_26.setName("26");

		JButton btn_36 = new JButton("36");
		boardPanel.add(btn_36);
		btn_36.setName("36");

		JButton btn_46 = new JButton("46");
		boardPanel.add(btn_46);
		btn_46.setName("46");

		JButton btn_56 = new JButton("56");
		boardPanel.add(btn_56);
		btn_56.setName("56");

		JButton btn_66 = new JButton("66");
		boardPanel.add(btn_66);
		btn_66.setName("66");

		JButton btn_76 = new JButton("76");
		boardPanel.add(btn_76);
		btn_76.setName("76");

		JButton btn_07 = new JButton("07");
		boardPanel.add(btn_07);
		btn_07.setName("07");

		JButton btn_17 = new JButton("17");
		boardPanel.add(btn_17);
		btn_17.setName("17");

		JButton btn_27 = new JButton("27");
		boardPanel.add(btn_27);
		btn_27.setName("27");

		JButton btn_37 = new JButton("37");
		boardPanel.add(btn_37);
		btn_37.setName("37");

		JButton btn_47 = new JButton("47");
		boardPanel.add(btn_47);
		btn_47.setName("47");

		JButton btn_57 = new JButton("57");
		boardPanel.add(btn_57);
		btn_57.setName("57");

		JButton btn_67 = new JButton("67");
		boardPanel.add(btn_67);
		btn_67.setName("67");

		JButton btn_77 = new JButton("77");
		boardPanel.add(btn_77);
		btn_77.setName("77");
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(250, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(10, 1, 0, 0));
		
		JButton btnMove_1 = new JButton("Move 1");
		panel_1.add(btnMove_1);
		
		JButton btnMove_2 = new JButton("Move 2");
		btnMove_2.setActionCommand("Move 2");
		panel_1.add(btnMove_2);
		
		JButton btnMove_3 = new JButton("Move 3");
		panel_1.add(btnMove_3);
		
		JButton btnMove_4 = new JButton("Move 4");
		panel_1.add(btnMove_4);
		
		JButton btnMove_5 = new JButton("Move 5");
		panel_1.add(btnMove_5);
		
		JButton btnMove_6 = new JButton("Move 6");
		panel_1.add(btnMove_6);
		
		JButton btnMove_7 = new JButton("Move 7");
		panel_1.add(btnMove_7);
		
		JButton btnMove_8 = new JButton("Move 8");
		panel_1.add(btnMove_8);
	}

}
