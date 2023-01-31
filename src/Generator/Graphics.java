package Generator;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import lombok.Data;

@Data
public class Graphics {

	private JFrame frame;
	private JTextField textField;
	private JButton generatebutton;
	private JLabel generatedpwdlabel;
	private SpringLayout springLayout;
	private JButton savefilebutton;
	private JCheckBox specialcharactercheckbox;
	private JCheckBox letterscheckbox;
	private JCheckBox numbercheckbox;
	private JRadioButton differentpwdradiobutton;
	private JSpinner passwordlengthspinner;
	private JLabel passwordlengthlabel;
	private JLabel numberofdiffcharlabel;
	private JSpinner numberofdifferentcaracterspinner;
	private JLabel languageapplabel;
	private JRadioButton samenumberradiobutton;
	private JSpinner replicasspinner;
	private JLabel replicaslabel;
	private Controller controller;
	private JButton openbutton;
	private JButton italianbutton;
	private JButton englishbutton;
	private JLabel lengthOfTheInputPassword;
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphics window = new Graphics();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Graphics() {
		controller = new Controller();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(controller.getTextofTitleAppLabel());
		frame.setBounds(100, 100, 542, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		generatedpwdlabel = new JLabel(controller.getTextofGeneratedPwdLabel());
		frame.getContentPane().add(generatedpwdlabel);
		
		generatebutton = new JButton(controller.getTextofGenerateButton());
		springLayout.putConstraint(SpringLayout.SOUTH, generatebutton, -26, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, generatedpwdlabel, -56, SpringLayout.NORTH, generatebutton);
		springLayout.putConstraint(SpringLayout.WEST, generatebutton, 59, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, generatebutton, 0, SpringLayout.EAST, generatedpwdlabel);
		generatebutton.addActionListener(controller.generaactionlistener(this));
		frame.getContentPane().add(generatebutton);
		
		savefilebutton = new JButton(controller.getTextofSaveFileButton());
		springLayout.putConstraint(SpringLayout.NORTH, savefilebutton, 0, SpringLayout.NORTH, generatebutton);
		savefilebutton.addActionListener(controller.Salvafile(this));
		frame.getContentPane().add(savefilebutton);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(controller.textChangeListener(this));
		springLayout.putConstraint(SpringLayout.WEST, textField, 193, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -177, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, generatedpwdlabel, -39, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, generatedpwdlabel);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		specialcharactercheckbox = new JCheckBox(controller.getTextofspecialcharactercheckbox());
		springLayout.putConstraint(SpringLayout.NORTH, specialcharactercheckbox, 61, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, specialcharactercheckbox, -42, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(specialcharactercheckbox);
		
		letterscheckbox = new JCheckBox(controller.getTextofLettersCheckbox());
		springLayout.putConstraint(SpringLayout.NORTH, letterscheckbox, 6, SpringLayout.SOUTH, specialcharactercheckbox);
		springLayout.putConstraint(SpringLayout.WEST, letterscheckbox, 0, SpringLayout.WEST, specialcharactercheckbox);
		frame.getContentPane().add(letterscheckbox);
		
		numbercheckbox = new JCheckBox(controller.getTextofNumbercheckbox());
		springLayout.putConstraint(SpringLayout.NORTH, numbercheckbox, 6, SpringLayout.SOUTH, letterscheckbox);
		springLayout.putConstraint(SpringLayout.WEST, numbercheckbox, 0, SpringLayout.WEST, specialcharactercheckbox);
		frame.getContentPane().add(numbercheckbox);
		
		differentpwdradiobutton = new JRadioButton(controller.getTextofDifferentPwdRadiobutton());
		springLayout.putConstraint(SpringLayout.SOUTH, differentpwdradiobutton, -236, SpringLayout.SOUTH, frame.getContentPane());
		differentpwdradiobutton.addChangeListener(controller.radioButtonGeneraPasswordDiversaDa(this));
		springLayout.putConstraint(SpringLayout.WEST, differentpwdradiobutton, 0, SpringLayout.WEST, generatedpwdlabel);
		frame.getContentPane().add(differentpwdradiobutton);
		
		passwordlengthspinner = new JSpinner();
		springLayout.putConstraint(SpringLayout.EAST, savefilebutton, 0, SpringLayout.EAST, passwordlengthspinner);
		frame.getContentPane().add(passwordlengthspinner);
		
		passwordlengthlabel = new JLabel(controller.getTextofPasswordLengthLabel());
		springLayout.putConstraint(SpringLayout.EAST, passwordlengthlabel, -100, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, passwordlengthspinner, -3, SpringLayout.NORTH, passwordlengthlabel);
		springLayout.putConstraint(SpringLayout.WEST, passwordlengthspinner, 5, SpringLayout.EAST, passwordlengthlabel);
		springLayout.putConstraint(SpringLayout.SOUTH, passwordlengthspinner, 0, SpringLayout.SOUTH, passwordlengthlabel);
		springLayout.putConstraint(SpringLayout.EAST, passwordlengthspinner, 47, SpringLayout.EAST, passwordlengthlabel);
		springLayout.putConstraint(SpringLayout.NORTH, passwordlengthlabel, 18, SpringLayout.SOUTH, numbercheckbox);
		frame.getContentPane().add(passwordlengthlabel);
		
		numberofdiffcharlabel = new JLabel(controller.getTextofNumberofDiffCharLabel());
		springLayout.putConstraint(SpringLayout.NORTH, numberofdiffcharlabel, 192, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, numberofdiffcharlabel, 60, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, numberofdiffcharlabel, 0, SpringLayout.EAST, differentpwdradiobutton);
		frame.getContentPane().add(numberofdiffcharlabel);
		
		numberofdifferentcaracterspinner = new JSpinner();
		springLayout.putConstraint(SpringLayout.NORTH, numberofdifferentcaracterspinner, 6, SpringLayout.SOUTH, numberofdiffcharlabel);
		springLayout.putConstraint(SpringLayout.WEST, numberofdifferentcaracterspinner, 99, SpringLayout.WEST, frame.getContentPane());
		numberofdifferentcaracterspinner.setEnabled(false);
		frame.getContentPane().add(numberofdifferentcaracterspinner);
		
		languageapplabel = new JLabel(controller.getTextofLanguageLabel());
		springLayout.putConstraint(SpringLayout.NORTH, languageapplabel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, languageapplabel, 0, SpringLayout.WEST, generatedpwdlabel);
		frame.getContentPane().add(languageapplabel);
		
		samenumberradiobutton = new JRadioButton(controller.getTextofSameNumberRadiobutton());
		springLayout.putConstraint(SpringLayout.EAST, numberofdifferentcaracterspinner, -153, SpringLayout.EAST, samenumberradiobutton);
		springLayout.putConstraint(SpringLayout.NORTH, samenumberradiobutton, 0, SpringLayout.NORTH, specialcharactercheckbox);
		springLayout.putConstraint(SpringLayout.WEST, samenumberradiobutton, 0, SpringLayout.WEST, generatedpwdlabel);
		frame.getContentPane().add(samenumberradiobutton);
		
		replicasspinner = new JSpinner();
		springLayout.putConstraint(SpringLayout.EAST, replicasspinner, 0, SpringLayout.EAST, passwordlengthspinner);
		frame.getContentPane().add(replicasspinner);
		
		replicaslabel = new JLabel(controller.getTextofReplicaslabel());
		springLayout.putConstraint(SpringLayout.EAST, replicaslabel, -100, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, replicasspinner, -3, SpringLayout.NORTH, replicaslabel);
		springLayout.putConstraint(SpringLayout.WEST, replicasspinner, 5, SpringLayout.EAST, replicaslabel);
		springLayout.putConstraint(SpringLayout.NORTH, replicaslabel, 25, SpringLayout.SOUTH, passwordlengthlabel);
		frame.getContentPane().add(replicaslabel);
		
		openbutton = new JButton(controller.getTextofOpenButton());
		openbutton.addActionListener(controller.apriFile(this));
		springLayout.putConstraint(SpringLayout.NORTH, openbutton, 0, SpringLayout.NORTH, generatebutton);
		springLayout.putConstraint(SpringLayout.WEST, openbutton, 81, SpringLayout.EAST, generatebutton);
		frame.getContentPane().add(openbutton);
		/*
		 * first need to add the source image, right click on the project, go to new -> Source Folder
		 * then create the source folder and put the files into it
		 * use this instruction for retrieve the source, for example here italy.jpg 
		 * is the name of a file contained in the resource folder
		 * */
		ImageIcon italyflag = controller.getTheItalyFlag();
		italianbutton = new JButton(italyflag);
		italianbutton.addActionListener(controller.italianButtonListener(this));
		springLayout.putConstraint(SpringLayout.WEST, italianbutton, 44, SpringLayout.EAST, languageapplabel);
		springLayout.putConstraint(SpringLayout.SOUTH, italianbutton, 5, SpringLayout.SOUTH, languageapplabel);
		frame.getContentPane().add(italianbutton);
		/*
		 * first need to add the source image, right click on the project, go to new -> Source Folder
		 * then create the source folder and put the files into it
		 * use this instruction for retrieve the source, for example here united_kindom.jpg 
		 * is the name of a file contained in the resource folder
		 * */
		ImageIcon englishflag = controller.getTheUkFlag();
		englishbutton = new JButton(englishflag);
		englishbutton.addActionListener(controller.englishButtonListener(this));
		springLayout.putConstraint(SpringLayout.WEST, englishbutton, 19, SpringLayout.EAST, italianbutton);
		springLayout.putConstraint(SpringLayout.SOUTH, englishbutton, 5, SpringLayout.SOUTH, languageapplabel);
		frame.getContentPane().add(englishbutton);
		
		lengthOfTheInputPassword = new JLabel(controller.getTextofInputPasswordLength());
		springLayout.putConstraint(SpringLayout.NORTH, lengthOfTheInputPassword, 0, SpringLayout.NORTH, generatedpwdlabel);
		springLayout.putConstraint(SpringLayout.WEST, lengthOfTheInputPassword, 6, SpringLayout.EAST, textField);
		frame.getContentPane().add(lengthOfTheInputPassword);
	}
}
