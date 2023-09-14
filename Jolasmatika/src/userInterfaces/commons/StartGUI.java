package userinterfaces.commons;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import businesslogic.Facade;
import userinterfaces.maze.LayoutGUI;

/**
 * 
 */
public class StartGUI {

	private JFrame frame;
	
	private JLabel title = new JLabel();
	private JLabel subtitle = new JLabel();
	
	private JButton btnStart = new JButton();
	private JComboBox<String> langList = new JComboBox<String>();
	
    /**
     * Constructor
     * @param appFacadeInterface 
     */
    public StartGUI(Facade appFacadeInterface) {
    	initialize(appFacadeInterface);
    }
    
    /**
     * Initializes the interface
     * @param appFacadeInterface 
     */
    private void initialize(Facade appFacadeInterface) {
    	update();
    	
    	String fontType = "Verdana";
    	
    	//frame
    	frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("JolasMAKER");

    	//labels
		frame.getContentPane().add(title);
		title.setBounds(125, 50, 300, 50);
		title.setFont(new Font(fontType, Font.PLAIN, 40));
		
		frame.getContentPane().add(subtitle);
		subtitle.setBounds(100, 125, 350, 50);
		subtitle.setFont(new Font(fontType, Font.PLAIN, 30));
		
		//langList
		langList.setSelectedIndex(0);
		frame.getContentPane().add(langList);
		langList.setBounds(175, 200, 150, 50);
		langList.setFont(new Font(fontType, Font.PLAIN, 25));
		
		langList.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<?> source = (JComboBox<?>) arg0.getSource();
				int choice = source.getSelectedIndex();
				switch (choice)
				{
					case 0: 
						Locale.setDefault(new Locale("eu"));
						break;
					case 1:
						Locale.setDefault(new Locale("es"));
						break;
					case 2:
						Locale.setDefault(new Locale("en"));
						break;
					default:
						Locale.setDefault(new Locale("eu"));
						break;
				}
				update();
					
			}

		});

		//btnStart
		frame.getContentPane().add(btnStart);
		btnStart.setBounds(125, 300, 250, 50);
		btnStart.setFont(new Font("Verdana", Font.PLAIN, 30));
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new LayoutGUI(appFacadeInterface);
			}
		});
    }
    
    //Updates the interface's text
    private void update() {
    	title.setText("JolasMAKER");
    	String bundleSource = "languages/Etiketak";
    	subtitle.setText(ResourceBundle.getBundle(bundleSource).getString("StartSubtitle"));
    	
		btnStart.setText(ResourceBundle.getBundle(bundleSource).getString("Start"));
		
		String[] langStrings = { 
				ResourceBundle.getBundle(bundleSource).getString("Basque"),
				ResourceBundle.getBundle(bundleSource).getString("Spanish"),
				ResourceBundle.getBundle(bundleSource).getString("English")
		};
		langList.removeAllItems();
		for(String s: langStrings)
		{
			langList.addItem(s);
		}
		
	}

}