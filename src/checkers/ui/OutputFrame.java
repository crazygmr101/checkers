package checkers.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import checkers.CheckersConstants;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;

public class OutputFrame extends JFrame implements CheckersConstants {

	private JPanel contentPane;
	private int cnt;
	public JEditorPane movesPane;
	public JLabel lastMove;
	public static OutputFrame frame;

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
					frame = new OutputFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	String inter;
	DecimalFormat df;
	
	public void add(String s) {
		lastMove.setText(s);
		inter += "<div>" +
				df.format(++cnt) + " " +
				s + "</div>";
		movesPane.setText("<html><body>" + inter + "</body></html>");
	}

	/**
	 * Create the frame.
	 */
	public OutputFrame() {
		df = new DecimalFormat("000");
		inter = "";
		cnt = 0;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Output");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lastMove = new JLabel("Last Move: ");
		lastMove.setBackground(Color.WHITE);
		contentPane.add(lastMove, BorderLayout.SOUTH);
		
		movesPane = new JEditorPane();
		HTMLEditorKit kit = new HTMLEditorKit();
		movesPane.setEditorKit(kit);
		movesPane.setEditable(false);
		movesPane.setEnabled(false);
		StyleSheet sheet = kit.getStyleSheet();
		sheet.addRule(".white {color: #666}");
		sheet.addRule(".black {color: #000}");
		sheet.addRule("div {font-family: monospace; font-size: 12pt; line-height: 1;}");
		contentPane.add(movesPane, BorderLayout.CENTER);
		this.setAlwaysOnTop(true);
	}

}
