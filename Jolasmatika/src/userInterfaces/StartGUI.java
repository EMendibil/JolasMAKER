package userInterfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import businessLogic.Facade;

/**
 * 
 */
public class StartGUI {

	private JFrame frame;
	
	private JLabel title = new JLabel();
	private JLabel subtitle = new JLabel();
	
	private JButton btnStart = new JButton();
	private JComboBox langList = new JComboBox();
	
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
    	
    	//frame
    	frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("JolasMAKER");

    	//labels
		frame.getContentPane().add(title);
		title.setBounds(125, 50, 300, 50);
		title.setFont(new Font("Verdana", Font.PLAIN, 40));
		
		frame.getContentPane().add(subtitle);
		subtitle.setBounds(100, 125, 350, 50);
		subtitle.setFont(new Font("Verdana", Font.PLAIN, 30));
		
		//langList
		langList.setSelectedIndex(0);
		frame.getContentPane().add(langList);
		langList.setBounds(175, 200, 150, 50);
		langList.setFont(new Font("Verdana", Font.PLAIN, 25));
		
		langList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox source = (JComboBox) arg0.getSource();
				int choice = source.getSelectedIndex();
				switch (choice)
				{
					case 0: 
						Locale.setDefault(new Locale("eus"));
						break;
					case 1:
						Locale.setDefault(new Locale("esp"));
						break;
					case 2:
						Locale.setDefault(new Locale("eng"));
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
    	subtitle.setText(ResourceBundle.getBundle("languages/Etiketak").getString("StartSubtitle"));
    	
		btnStart.setText(ResourceBundle.getBundle("languages/Etiketak").getString("Start"));
		
		String[] langStrings = { 
				ResourceBundle.getBundle("languages/Etiketak").getString("Basque"),
				ResourceBundle.getBundle("languages/Etiketak").getString("Spanish"),
				ResourceBundle.getBundle("languages/Etiketak").getString("English")
		};
		langList.removeAllItems();
		for(String s: langStrings)
		{
			langList.addItem(s);
		}
		
	}

}