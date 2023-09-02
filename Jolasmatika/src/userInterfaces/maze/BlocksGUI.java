package userInterfaces.maze;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.Facade;
import games.Blokea;

/**
 * 
 */
public class BlocksGUI {
	
	private JFrame frame;
	
	String bundleSource = "languages/Etiketak";
	
	private JButton btnBack = new JButton(ResourceBundle.getBundle(bundleSource).getString("Back"));
	private JButton btnContinue = new JButton(ResourceBundle.getBundle(bundleSource).getString("Continue"));
	
	private JLabel title = new JLabel(ResourceBundle.getBundle(bundleSource).getString("BlocksTitle"), SwingConstants.CENTER);
	private JLabel instructions = new JLabel(ResourceBundle.getBundle(bundleSource).getString("BlocksInstructions"), SwingConstants.CENTER);
	
	private JLabel lblMove = new JLabel(ResourceBundle.getBundle(bundleSource).getString("BlocksMove"));
	private JLabel lblTurn = new JLabel(ResourceBundle.getBundle(bundleSource).getString("BlocksTurn"));
	private JLabel lblIf = new JLabel(ResourceBundle.getBundle(bundleSource).getString("BlocksIf"));
	private JLabel lblIfElse = new JLabel(ResourceBundle.getBundle(bundleSource).getString("BlocksIfElse"));
	private JLabel lblRepUntil = new JLabel(ResourceBundle.getBundle(bundleSource).getString("BlocksRepUntil"));
	
	private JCheckBox chkMove = new JCheckBox();
	private JCheckBox chkTurn = new JCheckBox();
	private JCheckBox chkIf = new JCheckBox();
	private JCheckBox chkIfElse = new JCheckBox();
	private JCheckBox chkRepUntil = new JCheckBox();
	
	private JLabel lblKopurua = new JLabel(ResourceBundle.getBundle(bundleSource).getString("BlocksAmount"), SwingConstants.CENTER);
	private JComboBox<Comparable> kopList = new JComboBox<Comparable>();
	
	private JFrame previousFrame;
	private Facade facadeImplementation;
	
    /**
     * Constructor
     * @param appFacadeInterface 
     */
    public BlocksGUI(Facade appFacadeInterface, JFrame previousFrame) {
    	this.facadeImplementation = appFacadeInterface;
    	this.previousFrame = previousFrame;
    	initialize();
    }
    
    /**
     * Initializes the interface
     */
    private void initialize() {
    	
    	//frame
    	frame = new JFrame();
    	frame.setSize(1000, 1000);
    	frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("JolasMAKER");
		
		String fontType = "Verdana";
		
		//labels
		frame.getContentPane().add(title);
		title.setBounds(150, 50, 700, 50);
		title.setFont(new Font(fontType, Font.PLAIN, 40));

		frame.getContentPane().add(instructions);
		instructions.setBounds(100, 175, 800, 50);
		instructions.setFont(new Font(fontType, Font.PLAIN, 30));
		
		//blockSelector
		Container blocks = new Container();
		GridLayout blocksLayout = new GridLayout(5,2);
		blocks.setLayout(blocksLayout); //Creates block selector layout
		
		blocks.add(lblMove); //Add labels to the block selector
		blocks.add(chkMove);
		lblMove.setFont(new Font(fontType, Font.PLAIN, 25));
		
		blocks.add(lblTurn);
		blocks.add(chkTurn);
		lblTurn.setFont(new Font(fontType, Font.PLAIN, 25));
		
		blocks.add(lblIf);
		blocks.add(chkIf);
		lblIf.setFont(new Font(fontType, Font.PLAIN, 25));
		
		blocks.add(lblIfElse);
		blocks.add(chkIfElse);
		lblIfElse.setFont(new Font(fontType, Font.PLAIN, 25));
		
		blocks.add(lblRepUntil);
		blocks.add(chkRepUntil);
		lblRepUntil.setFont(new Font(fontType, Font.PLAIN, 25));
		
		
		
		blocks.setBounds(300, 300, 700, 300);
		frame.add(blocks);
		
		//kopList
		frame.getContentPane().add(lblKopurua);
		lblKopurua.setBounds(240, 650, 250, 50);
		lblKopurua.setFont(new Font(fontType, Font.PLAIN, 30));
		
		frame.getContentPane().add(kopList);
		kopList.setBounds(510, 650, 250, 50);
		kopList.setFont(new Font(fontType, Font.PLAIN, 30));
		kopList.addItem("Inf");
		for(int i=1; i<=10; i++) kopList.addItem(i);
		
		//btnBack
		frame.getContentPane().add(btnBack);
		btnBack.setBounds(240, 800, 250, 50);
		btnBack.setFont(new Font(fontType, Font.PLAIN, 30));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				previousFrame.setVisible(true);
			}
		});
		
		//btnContinue
		frame.getContentPane().add(btnContinue);
		btnContinue.setBounds(510, 800, 250, 50);
		btnContinue.setFont(new Font(fontType, Font.PLAIN, 30));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Blokea> blokeak = new ArrayList<Blokea>();
				
				//Inserts selected blocks into array
				if (chkMove.isSelected()) blokeak.add(new Blokea("maze_moveForward", ResourceBundle.getBundle("languages/Etiketak").getString("BlocksMove"), false, false, "blue", ""));
				if (chkTurn.isSelected()) blokeak.add(new Blokea("maze_turn", ResourceBundle.getBundle("languages/Etiketak").getString("BlocksTurn"), false, false, "blue", ""));
				if (chkIf.isSelected()) blokeak.add(new Blokea("maze_if", ResourceBundle.getBundle("languages/Etiketak").getString("BlocksIf"), false, false, "green", ""));
				if (chkIfElse.isSelected()) blokeak.add(new Blokea("maze_ifElse", ResourceBundle.getBundle("languages/Etiketak").getString("BlocksIfElse"), false, false, "green", ""));
				if (chkRepUntil.isSelected()) blokeak.add(new Blokea("maze_forever", ResourceBundle.getBundle("languages/Etiketak").getString("BlocksRepUntil"), false, false, "red", ""));
				
				facadeImplementation.blokeakTxertatu(blokeak, kopList.getSelectedIndex());
				facadeImplementation.bihurtu("patata", "maze");
			}
		});
    }


}