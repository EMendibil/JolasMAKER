package userinterfaces.maze;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import businesslogic.Facade;
import exceptions.WrongLayoutException;
import userinterfaces.commons.BlocksGUI;
import userinterfaces.commons.StartGUI;

/**
 * 
 */
public class LayoutGUI {

	private JFrame frame;
	
	String bundleSource = "languages/Etiketak";
	
	private JButton btnBack = new JButton(ResourceBundle.getBundle(bundleSource).getString("Back"));
	private JButton btnContinue = new JButton(ResourceBundle.getBundle(bundleSource).getString("Continue"));
	
	private boolean startSet = false;
	private boolean endSet = false;
	private int[][] mazeMatrix = new int[7][7];
	
	private JLabel title = new JLabel(ResourceBundle.getBundle(bundleSource).getString("LayoutTitle"), SwingConstants.CENTER);
	private JLabel instructions = new JLabel(ResourceBundle.getBundle(bundleSource).getString("LayoutStart"), SwingConstants.CENTER);
	private JLabel lblError = new JLabel(ResourceBundle.getBundle(bundleSource).getString("LayoutError"), SwingConstants.CENTER);
	
	private Facade facadeImplementation;
	private BlocksGUI nextFrame;
	
    /**
     * Constructor
     * @param appFacadeInterface 
     */
    public LayoutGUI(Facade appFacadeInterface) {
    	this.facadeImplementation = appFacadeInterface;
    	initialize();
    }

	/**
     * Initializes the interface
     */
    private void initialize() {
    	
    	String fontName = "Verdana";
    	
    	//frame
    	frame = new JFrame();
    	frame.setSize(1000, 1000);
    	frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("JolasMAKER");
		
    	JScrollPane jsp = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	frame.getContentPane().add(jsp);
		
		//labels
		frame.getContentPane().add(title);
		title.setBounds(300, 50, 400, 50);
		title.setFont(new Font(fontName, Font.PLAIN, 40));

		frame.getContentPane().add(instructions);
		instructions.setBounds(150, 175, 700, 50);
		instructions.setFont(new Font(fontName, Font.PLAIN, 30));
		
		frame.getContentPane().add(lblError);
		lblError.setBounds(150, 725, 700, 50);
		lblError.setFont(new Font(fontName, Font.BOLD, 25));
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		
		//maze
		Container maze = new Container();
		GridLayout mazeLayout = new GridLayout(7,7);
		maze.setLayout(mazeLayout); //Creates maze layout
		JButton btnMaze;
		for (int j = 0; j < 7; j++) { //Adds buttons that users can press to create a layout
			for (int i = 0; i < 7; i++) {
				btnMaze = new JButton();
				btnMaze.setName(i + " " + j);
				btnMaze.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JButton source = (JButton) arg0.getSource();
						String[] coordinates = source.getName().split(" ");
						int i = Integer.parseInt(coordinates[0]);
						int j = Integer.parseInt(coordinates[1]); //receives coordinates of button in the grid
						
						if(mazeMatrix[i][j] == 0)
						{
							if (startSet && endSet) 
							{
								source.setBackground(Color.blue); 
								mazeMatrix[i][j] = 1;
							}
							else {
								if (!startSet)
								{
									source.setBackground(Color.green);
									startSet = true;
									mazeMatrix[i][j] = 2;
									if(endSet) {btnContinue.setEnabled(true); instructions.setText(ResourceBundle.getBundle(bundleSource).getString("LayoutPath"));}
									else instructions.setText(ResourceBundle.getBundle(bundleSource).getString("LayoutEnd"));
								}
								else {
									source.setBackground(Color.red);
									endSet = true;
									mazeMatrix[i][j] = 3;
									btnContinue.setEnabled(true);
									instructions.setText(ResourceBundle.getBundle(bundleSource).getString("LayoutPath"));
								}

							}
						}
						else {
							if(source.getBackground() == Color.green) {startSet = false; btnContinue.setEnabled(false); instructions.setText(ResourceBundle.getBundle(bundleSource).getString("LayoutStart"));}
							if(source.getBackground() == Color.red) {endSet = false; btnContinue.setEnabled(false); instructions.setText(ResourceBundle.getBundle(bundleSource).getString("LayoutEnd"));}
							source.setBackground(null);
							mazeMatrix[i][j] = 0;
						}
						
					}
				});
				maze.add(btnMaze);
			}
		}
		
		maze.setBounds(300, 300, 400, 400);
		frame.add(maze);
		
		//btnBack
		frame.getContentPane().add(btnBack);
		btnBack.setBounds(240, 800, 250, 50);
		btnBack.setFont(new Font(fontName, Font.PLAIN, 30));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new StartGUI(facadeImplementation);
			}
		});
		
		//btnContinue
		frame.getContentPane().add(btnContinue);
		btnContinue.setBounds(510, 800, 250, 50);
		btnContinue.setFont(new Font(fontName, Font.PLAIN, 30));
		btnContinue.setEnabled(false);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					facadeImplementation.labirintoaSortu(mazeMatrix);
					lblError.setVisible(false);
					
					frame.setVisible(false);
					if(nextFrame != null) nextFrame.getFrame().setVisible(true);
					else nextFrame = new BlocksGUI(facadeImplementation, frame);
				}
				catch (WrongLayoutException e) {
					lblError.setVisible(true);
				}
				
			}
		});
    }


}