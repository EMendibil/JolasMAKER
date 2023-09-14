package userInterfaces.commons;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import businessLogic.Facade;
import exceptions.KeyNotFoundException;

/**
 * Interface to specify which blocks will be used and their maximum amount
 */
public class ConfirmGUI {
	
	private JFrame frame;
	
	String bundleSource = "languages/Etiketak";
	
	private JButton btnBack = new JButton(ResourceBundle.getBundle(bundleSource).getString("Back"));
	private JButton btnContinue = new JButton(ResourceBundle.getBundle(bundleSource).getString("Finish"));
	
	private JLabel title = new JLabel(ResourceBundle.getBundle(bundleSource).getString("ConfirmTitle"), SwingConstants.CENTER);
	private JLabel instructions = new JLabel(ResourceBundle.getBundle(bundleSource).getString("ConfirmInstructions"), SwingConstants.CENTER);
	
	private JLabel lblName = new JLabel(ResourceBundle.getBundle(bundleSource).getString("ConfirmName"));
	private JLabel lblHint = new JLabel(ResourceBundle.getBundle(bundleSource).getString("ConfirmHint"));
	
	private JLabel lblError = new JLabel("", SwingConstants.CENTER);

	private JTextField txtName = new JTextField();
	private JTextField txtHint = new JTextField();
	
	//private JLabel loadIcon; Unused, because it causes games to create slower
	
	private JFrame previousFrame;
	private Facade facadeImplementation;
	
    /**
     * Constructor
     * @param appFacadeInterface 
     * @param previousFrame the previous interface
     */
    public ConfirmGUI(Facade appFacadeInterface, JFrame previousFrame) {
    	this.facadeImplementation = appFacadeInterface;
    	this.previousFrame = previousFrame;
    	initialize();
    }
    
    public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
     * Initializes the interface
     */
    private void initialize() {
    	
    	//frame
    	frame = new JFrame();
    	frame.setSize(1000, 1000);
    	frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		
		frame.getContentPane().add(lblError);
		lblError.setBounds(50, 625, 900, 60);
		lblError.setFont(new Font("Verdana", Font.BOLD, 25));
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		
		//name		
		lblName.setFont(new Font(fontType, Font.PLAIN, 25));
		lblName.setBounds(50, 350, 800, 30);
		frame.getContentPane().add(lblName);
		txtName.setBounds(275, 350, 400, 30);
		frame.getContentPane().add(txtName);
		
		//starting hint
		lblHint.setFont(new Font(fontType, Font.PLAIN, 25));
		lblHint.setBounds(50, 500, 800, 30);
		frame.getContentPane().add(lblHint);
		txtHint.setBounds(275, 500, 400, 30);
		frame.getContentPane().add(txtHint);
		
		//btnBack
		frame.getContentPane().add(btnBack);
		btnBack.setBounds(240, 800, 250, 50);
		btnBack.setFont(new Font(fontType, Font.PLAIN, 30));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblError.setVisible(false);
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
				lblError.setVisible(false);
				String name = txtName.getText();
				String hint = txtHint.getText();
				if (name.isEmpty())		 {lblError.setText(ResourceBundle.getBundle(bundleSource).getString("ConfirmNameEmpty")); lblError.setVisible(true);}
				else if (hint.isEmpty()) {lblError.setText(ResourceBundle.getBundle(bundleSource).getString("ConfirmHintEmpty")); lblError.setVisible(true);}
				else {
					
					try {
						facadeImplementation.bihurtu(name, hint, "maze");
					} catch (KeyNotFoundException | InterruptedException | IOException e) {
						lblError.setText(ResourceBundle.getBundle(bundleSource).getString("ConfirmError"));
						lblError.setVisible(true);
						Thread.currentThread().interrupt();
					}
					btnBack.setVisible(false);
					btnContinue.setVisible(false);
					lblName.setVisible(false);
					lblHint.setVisible(false);
					lblError.setVisible(false);
					txtName.setVisible(false);
					txtHint.setVisible(false);
					
					instructions.setText(ResourceBundle.getBundle(bundleSource).getString("ConfirmComplete"));
					//loadIcon.setVisible(true); Unused for now, because it makes creating the game too slow.
				}		
			}
		});
    }


}