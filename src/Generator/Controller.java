package Generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controller {
	Model model;
	/**
	 * The constructor initialize a new model
	 */
	public Controller() {
		super();
		model = new Model();
	//	model.getLanguage().Italian();
	}
	/**
	 * Count how many character are equals in the two input string
	 * @param str1 input string
	 * @param str2 input string
	 * @return the number of different characters
	 */
	private int numberofEqualsCharsInTwoString(String str1, String str2) {
		int numerofequalschars = 0;
		for(int i = 0 ; i< str1.length(); i++)
			for(int j = 0 ; j< str2.length(); j++)
				if(str1.charAt(i) == str2.charAt(j))
					numerofequalschars++;
		return numerofequalschars;
	}
	/**
	 * The action listener for the genera button
	 * @param view the view (GUI)
	 * @return the action listener
	 */
	public ActionListener generaactionlistener(Graphics view) {
		ActionListener actionlistener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// if the string is blank or we don't need a different password with respect to the password in the textfield
				if((view.getTextField().getText().isEmpty() && view.getDifferentpwdradiobutton().isSelected()) || view.getDifferentpwdradiobutton().isSelected() == false) {
					try {
						view.getTextField().setText(generate(view.getNumbercheckbox().isSelected(), view.getLetterscheckbox().isSelected(),view.getSpecialcharactercheckbox().isSelected(), (int) view.getPasswordlengthspinner().getValue(), view.getSamenumberradiobutton().isSelected(), (int)view.getReplicasspinner().getValue()));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(view.getFrame(),e1.getMessage(),model.getLanguage().getInputerror(),JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					try {
					
					if((int) view.getTextField().getText().length() - (int) view.getNumberofdifferentcaracterspinner().getValue() >= 4) {
					while(true) {
						model.setPassword(generate(view.getNumbercheckbox().isSelected(), view.getLetterscheckbox().isSelected(),view.getSpecialcharactercheckbox().isSelected(), (int) view.getPasswordlengthspinner().getValue(), view.getSamenumberradiobutton().isSelected(), (int)view.getReplicasspinner().getValue()));
						if(numberofEqualsCharsInTwoString(view.getTextField().getText(), model.getPassword()) >= (int) view.getNumberofdifferentcaracterspinner().getValue())
							break;
					}
					view.getTextField().setText(model.getPassword());
					}
					else if((int) view.getNumberofdifferentcaracterspinner().getValue() > view.getTextField().getText().length()) {
						JOptionPane.showMessageDialog(view.getFrame(),model.getLanguage().getTomuchdiffchar(),model.getLanguage().getInputerror(),JOptionPane.ERROR_MESSAGE);
					}else {
						model.setPassword(view.getTextField().getText());
						view.getTextField().setText(generateVeryDifferentString(model.getPassword(),view.getNumbercheckbox().isSelected(), view.getLetterscheckbox().isSelected(),view.getSpecialcharactercheckbox().isSelected()));
					}
					}catch (Exception exept) {
						JOptionPane.showMessageDialog(view.getFrame(),exept.getMessage(),model.getLanguage().getInputerror(),JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
		return actionlistener;
	}
	/**
	 * Listener for the radiobutton generate password different from...
	 * @param view the view (GUI)
	 * @return the listener associated to the change of state of the radiobutton
	 */
	public ChangeListener radioButtonGeneraPasswordDiversaDa(Graphics view) {
		ChangeListener radiobuttonlistener = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (view.getDifferentpwdradiobutton().isSelected())
					view.getNumberofdifferentcaracterspinner().setEnabled(true);
				else
					view.getNumberofdifferentcaracterspinner().setEnabled(false);
				
			}
		};
		return radiobuttonlistener;
	}
	/**
	 * Listener for the save button
	 * @param view
	 * @return
	 */
	public ActionListener Salvafile(Graphics view) {
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int userSelection;
					int result = 0;
					JFileChooser jfilechooser = new JFileChooser();
					jfilechooser.setDialogTitle(model.getLanguage().getDialogtitletext());
					FileNameExtensionFilter filenameextensionfilter = new FileNameExtensionFilter(model.getLanguage().getExstentiontext(), "txt");
					jfilechooser.addChoosableFileFilter(filenameextensionfilter);
					userSelection = jfilechooser.showSaveDialog(view.getFrame());
					if (userSelection == JFileChooser.APPROVE_OPTION) {
					    File fileToSave = jfilechooser.getSelectedFile();
					    javax.swing.filechooser.FileFilter extension = jfilechooser.getFileFilter();
					    if(fileToSave.exists()) {
					    	/* Show a confirm dialog if the file already exist in which I ask at the user
					    	* if it would like to overwrite the selected file
					    	*/
					    	result = JOptionPane.showConfirmDialog(view.getFrame(),model.getLanguage().getFileoverwritebodytext(),model.getLanguage().getFileovervritetitletext(),JOptionPane.YES_NO_CANCEL_OPTION);
						    if(result == JOptionPane.YES_OPTION){
						    	// if the client click on 'Yes' overwrite the target file
			                	FileWriter filewriter = new FileWriter(fileToSave);
			                	filewriter.write(view.getTextField().getText());
			                	filewriter.flush();
			                	filewriter.close();
			                }
					    }else {
		                	if(filenameextensionfilter == extension)
		                		fileToSave = new File(jfilechooser.getSelectedFile()+"."+"txt");
		                	FileWriter filewriter = new FileWriter(fileToSave);
		                	filewriter.write(view.getTextField().getText());
		                	filewriter.flush();
		                	filewriter.close();
					    }
					}
				}catch (Exception ex) {
			    	   JOptionPane.showMessageDialog(view.getFrame(),ex.getMessage(),model.getLanguage().getIotitletext(),JOptionPane.ERROR_MESSAGE);
			       }
			}
		};
		return actionListener;
	}
	public ActionListener apriFile(Graphics view) {
		ActionListener actionlistener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfilechooser = new JFileChooser();
				jfilechooser.setDialogTitle(model.getLanguage().getOpendialogtitle());
				FileNameExtensionFilter filenameextensionfilter = new FileNameExtensionFilter(model.getLanguage().getExstentiontext(), "txt");
				jfilechooser.addChoosableFileFilter(filenameextensionfilter);
				int userSelection = jfilechooser.showOpenDialog(view.getFrame());
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					try {
						// open the selected file
						FileReader openfile = new FileReader(jfilechooser.getSelectedFile());
						BufferedReader bufferreader = new BufferedReader(openfile);
						// set text containted in the selected file in the textfield
						view.getTextField().setText(bufferreader.readLine());
					} catch (IOException exeption) {
						JOptionPane.showMessageDialog(view.getFrame(),model.getLanguage().getIobodytext(),model.getLanguage().getIotitletext(),JOptionPane.ERROR_MESSAGE);
					} catch (NullPointerException nullpointerexception) {
						JOptionPane.showMessageDialog(view.getFrame(),model.getLanguage().getNofileselectedexceptionbodytext(),model.getLanguage().getNofileselectedexceptiontitletext(),JOptionPane.WARNING_MESSAGE);
					}
				}	
			}
		};
		return actionlistener;
	}
	// Methods that set the text to element of the view
	/**
	 * Get the text of the label generate password in the chosen languages
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofGeneratedPwdLabel(){
		return model.getLanguage().getGeneratedpwdlabeltext();
	}
	/**
	 * The text that will be insert in the button generate
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofGenerateButton() {
		return model.getLanguage().getGeneratebutton();
	}
	/**
	 * The text that will be insert in the button save
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofSaveFileButton() {
		return model.getLanguage().getSavefilebuttontext();
	}
	/**
	 * The text that will be show in the label close to the special character's checkbook
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofspecialcharactercheckbox() {
		return model.getLanguage().getSpecialcharactercheckboxtext();
	}
	/**
	 * The text that will be show in the label close to the letters's checkbook
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofLettersCheckbox() {
		return model.getLanguage().getLetterscheckboxtext();
	}
	/**
	 * The text that will be show in the label close to the number's checkbook
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofNumbercheckbox() {
		return model.getLanguage().getNumbercheckboxtext();
	}
	/**
	 * The text that will be show in the label close to the radio button that allow you to
	 * ask for a password that is # character different than the password in the textfield
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofDifferentPwdRadiobutton() {
		return model.getLanguage().getDifferentpwdradiobuttontext();
	}
	/**
	 * The text of the label that tells you that the spinner close to it 
	 * it is used for choosing the length of the password
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofPasswordLengthLabel() {
		return model.getLanguage().getPasswordlengthlabeltest();
	}
	/**
	 * The text of the label that tells you that the spinner close to it
	 * it is used for choosing the number of different character with respect to 
	 * the old password that we desired
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofNumberofDiffCharLabel() {
		return model.getLanguage().getNumberofdiffcharlabeltext();
	}
	/**
	 * The text that represent the title of the application
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofTitleAppLabel() {
		return model.getLanguage().getTitleapplabeltext();
	}
	/**
	 * The text of the radiobutton that the user select if it would like
	 * to have the same numbers of the selected kind of charters
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofSameNumberRadiobutton() {
		return model.getLanguage().getSamenumberradiobuttontext();
	}
	/**
	 * The text of the label that tell at the user that the spinner close it is used
	 * for set up the number of equals character allowed in the password
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofReplicaslabel() {
		return model.getLanguage().getReplicaslabeltext();
	}
	/**
	 * The text of the open button
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofOpenButton() {
		return model.getLanguage().getOpenbuttontext();
	}
	/**
	 * The language change label
	 * @return a string that will put in the target element of the view
	 */
	public String getTextofLanguageLabel() {
		return model.getLanguage().getLanguage();
	}
	/**
	 * The length of the input password
	 * @return the string for the target component
	 */
	public String getTextofInputPasswordLength() {
		return String.valueOf(model.getInputPasswordLen());
	}
	/**
	 * This method change the text of the elements of the target view
	 * @param view the Graphics object for which will be changed the texts of the elements
	 */
	private void UpdateView(Graphics view) {
		view.getFrame().setTitle(getTextofTitleAppLabel());
		view.getSavefilebutton().setText(getTextofSaveFileButton());
		view.getGeneratebutton().setText(getTextofGenerateButton());
		view.getGeneratedpwdlabel().setText(getTextofGeneratedPwdLabel());
		view.getSpecialcharactercheckbox().setText(getTextofspecialcharactercheckbox());
		view.getLetterscheckbox().setText(getTextofLettersCheckbox());
		view.getNumbercheckbox().setText(getTextofNumbercheckbox());
		view.getDifferentpwdradiobutton().setText(getTextofDifferentPwdRadiobutton());
		view.getPasswordlengthlabel().setText(getTextofPasswordLengthLabel());
		view.getNumberofdiffcharlabel().setText(getTextofNumberofDiffCharLabel());
		view.getSamenumberradiobutton().setText(getTextofSameNumberRadiobutton());	
		view.getReplicaslabel().setText(getTextofReplicaslabel());
		view.getOpenbutton().setText(getTextofOpenButton());
		view.getLanguageapplabel().setText(getTextofLanguageLabel());
	}
	/**
	 * The listener that changes the language of the application
	 * @param view
	 * @return The Action listener
	 */
	public ActionListener englishButtonListener(Graphics view) {
		ActionListener actionlistener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				english();
				UpdateView(view);
				
			}
		};
		return actionlistener;
	}
	/**
	 * The listener that changes the language of the application
	 * @param view
	 * @return The Action listener
	 */
	public ActionListener italianButtonListener(Graphics view) {
		ActionListener actionlistener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				italian();
				UpdateView(view);
				
			}
		};
		return actionlistener;
	}
	/**
	 * The listener that change the label of the length of the input password when it is changed
	 * @param view
	 * @return The listener
	 */
	public DocumentListener textChangeListener(Graphics view) {
		DocumentListener documentListener = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// Set the length of the input password when the content of the textfield changes 
				model.setInputPasswordLen(view.getTextField().getText().length());
				//update view component
				view.getLengthOfTheInputPassword().setText(getTextofInputPasswordLength());
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// Set the length of the input password when the content of the textfield changes 
				model.setInputPasswordLen(view.getTextField().getText().length());
				//update view component
				view.getLengthOfTheInputPassword().setText(getTextofInputPasswordLength());
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// Set the length of the input password when the content of the textfield changes 
				model.setInputPasswordLen(view.getTextField().getText().length());
				//update view component
				view.getLengthOfTheInputPassword().setText(getTextofInputPasswordLength());
				
			}
		};
		return documentListener;
	}
	/**
	 * Check if a int is a letter
	 * @param a the number to check
	 * @return true if the number is a representation of a letter (upper or lower case) in the Ascii table
	 * 		   false if the number is not the representation of a letter in the Ascii table
	 */
	private boolean isaLetter(int a) {
		if(a >=65 && a<=90 || a>=97 && a<=122)
			return true;
		else
			return false;
	}
	/**
	 * Check if a int is a representation of a integer in the Ascii table
	 * @param a the number to check
	 * @return true if the number is a representation of a integer in the Ascii table
	 * 		   false if the number is not the representation of a integer in the Ascii table
	 */
	private boolean isaInt(int a) {
		if(a >= 48 && a <= 57)
			return true;
		else
			return false;
	}
	/**
	 * Check if a int is a representation of a special character in the Ascii table
	 * @param a the number to check
	 * @return true if the number is a representation of a special character in the Ascii table
	 * 		   false if the number is not a representation of a special character in the Ascii table
	 */
	private boolean isaSpecialCharacter(int a) {
		if(a >= 33 && a <= 47 || a >= 58 && a <= 64 || a >= 91 && a <= 96 || a >= 123 && a <= 126)
			return true;
		else
			return false;
	}
	/**
	 * Control if a target character is already in a string
	 * put if the character if the number of character is less or equals than parameter replicas
	 * @param candidate the character that is candidate to become part of the string
	 * @param replicas: the number of equals character that we desired
	 */
	private String validate(int candidate, int replicas, String passw) {
		char a = (char) candidate;
		int numerofit = 0;
		for (int i = 0; i< passw.length(); i++ )
			if(a == passw.charAt(i))
				numerofit++;
		if(numerofit <= replicas)
			return passw.concat(String.valueOf(a));
		else 
			return passw;
	}
	/**
	 * Check how much numbers are contained in the input string
	 * @param passw: The input string
	 * @return Return the number of integer contained in the string
	 */
	private int numerofint(String passw) {
		int len = 0;
		for (int i = 0; i< passw.length(); i++ )
			if(isaInt((int)passw.charAt(i)) == true)
				len++;
		return len;
	}
	/**
	 * Check how much special character are contained in the string
	 * @param passw: The input string
	 * @return the number of special character contained in the string
	 */
	private int numerofspecialchar(String passw) {
		int len = 0;
		for (int i = 0; i< passw.length(); i++ )
			if(isaSpecialCharacter((int)passw.charAt(i)) == true)
				len++;
		return len;
	}
	/**
	 * Check how much letters are contained in the string
	 * @param passw: The input string
	 * @return the number of letters contained in the string
	 */
	private int numerofchar(String passw) {
		int len = 0;
		for (int i = 0; i< passw.length(); i++ )
			if(isaLetter((int)passw.charAt(i)) == true)
				len++;
		return len;
	}
	/**
	 * Check if we need another integer number
	 * @param maxnumerofint the maximum number of desired integer
	 * @param passw: The input string
	 * @return true: if the number of integers is less or equals than the maximum number of desired integer
	 * 		   false: otherwise 
	 */
	private boolean anotherInt(int maxnumerofint, String passw) {
		if(numerofint(passw) < maxnumerofint)
			return true;
		else
			return false;
	}
	/**
	 * Check if we need another letters
	 * @param maxnumerofletter the maximum number of desired letters
	 * @param passw: The input string
	 * @return true: if the number of letters is less or equals than the maximum number of desired letters
	 * 		   false: otherwise 
	 */
	private boolean anotherLetter(int maxnumerofletter, String passw) {
		if(numerofchar(passw) < maxnumerofletter)
			return true;
		else
			return false;
	}
	/**
	 * Check if we need another special character
	 * @param maxnumerofspecialchar the maximum number of desired special character
	 * @param passw: The input string
	 * @return true: if the number of letters is less or equals than the maximum number of desired special character
	 * 		   false: otherwise 
	 */
	private boolean anotherSpecialChar(int maxnumerofspecialchar, String passw) {
		if(numerofspecialchar(passw) < maxnumerofspecialchar)
			return true;
		else
			return false;
	}
	/**
	 * Since we put letters integer and special characters sequentially this will return a string with some
	 * deterministic pattern, to avoid this we do a further shuffle of the characters
	 * @param passw: The input password
	 * @return The shuffled password
	 */
	private String scramble(String passw){
		  String shuffledpassword = new String();
		  // create new arraylist in order to do the shuffle
		  List<Character> letters = new ArrayList<>();
		  // I put all the character of the string in the list
		  for (char c : passw.toCharArray()) {
			  letters.add(c);
		  }
		  // I do the shuffle operation
		  Collections.shuffle(letters);
		  // Finally I reput the shuffled character in the string
		  for (Character c : letters)
			  shuffledpassword = shuffledpassword.concat(c.toString());
		   return shuffledpassword;
	}
	/**
	 * Generates a string that have all character different to the starting string
	 * this method don't check replicas
	 * @param string the target string
	 * @return
	 */
	private String generateVeryDifferentString(String string, boolean isnumersenabled, boolean ischaracterenabled, boolean isspecialcharenabl) {
		String newstring = new String();
		int element = 0;
		  for (int i = 0; i < string.length(); i++) {
			  while(true) {
				  	if(element < 110)
					  element = (int)string.charAt(i) + ThreadLocalRandom.current().nextInt(127);
				  	else
				  	  element = (int)string.charAt(i) - ThreadLocalRandom.current().nextInt(127);
				  	if(isaInt(element) && isnumersenabled || isaLetter(element) && ischaracterenabled || isaSpecialCharacter(element) && isspecialcharenabl)
				  		break;
			  }
			  // add character to the string
			  newstring = newstring.concat(String.valueOf((char)element));
		  }
		 return newstring; 
	}
	/**
	 * Return a string of random character composed by the selected type of character
	 * @param isnumersenabled: if is true in the returning string there will be also numbers
	 * @param ischaracterenabled: if is true in the returning string there will be also letters
	 * @param isspecialcharenabl: if is true in the returning string there will be also special character
	 * @param len: the length of the returning string 
	 * @return Return a string of random character
	 * @throws Exception: if the parameter len is null negative or zero
	 * 					  if the parameters isnumersenabled ischaracterenabled isspecialcharenabl are all false
	 */
	private String generate(boolean isnumersenabled, boolean ischaracterenabled, boolean isspecialcharenabl, int len, boolean equalsdistr, int replicas) throws Exception {
		// Initialize every execution since we are using the directly the class variable 
		String passw = "";
		// Maximum number of int in the target string
		int maxnumerofint;
		// Maximum number of letters in the target string
		int maxnumerofchar;
		// Maximum number of special character in the target string
		int maxnumerofsecialchar;
		// The split variable determines the denominator of the maximum number of each type of characters
		int split = 0;
		if(isnumersenabled == true)
			split ++;
		if(ischaracterenabled == true)
			split ++;
		if(isspecialcharenabl == true)
			split ++;
		// If len is less or equal 0 throw an exception
		if(len <= 0)
			throw new Exception(model.getLanguage().getLenexecptiontext());
		// If the user did't flag any check throw an exception
		if (isnumersenabled == false && ischaracterenabled == false && isspecialcharenabl == false)
			throw new Exception(model.getLanguage().getSelectionexceptiontext());
		// maxnumerofint will be the length of the desired string divided by split (avoid exception if split is 0
		// because if split is 0 I already throw an exception)
		maxnumerofint = (int) Math.ceil((double) len/split);
		// maxnumerofchar will be the length of the desired string divided by split (avoid exception if split is 0
		// because if split is 0 I already throw an exception)
		maxnumerofchar = (int) Math.ceil((double) len/split);
		// maxnumerofsecialchar will be the length of the desired string divided by split (avoid exception if split is 0
		// because if split is 0 I already throw an exception)
		maxnumerofsecialchar = (int) Math.ceil((double) len/split);
		if(equalsdistr == false) {
			// Execute these instructions since the password's length will not reach the desired one
			while(passw.length() < len) {
				// generate the random integer number between 0 and 127
				int randomgeneratornumber = ThreadLocalRandom.current().nextInt(127);
				// check the conditions
				if(isaInt(randomgeneratornumber) && isnumersenabled || isaLetter(randomgeneratornumber) && ischaracterenabled || isaSpecialCharacter(randomgeneratornumber) && isspecialcharenabl)
					passw = validate(randomgeneratornumber,replicas, passw);
			}
		}
		else {
			// Execute these instructions since the password's length will not reach the desired one
			while(passw.length() < len) {
				// generate the random integer number between 0 and 127
				int randomgeneratornumber = ThreadLocalRandom.current().nextInt(127);
				// the condition in order to have the desired password
				// in this case I need that the number must be an integer and the integer flag is enable and we
				// are under the max number of allowed integer
				boolean anotherint = anotherInt(maxnumerofint, passw);
				if(isaInt(randomgeneratornumber) && isnumersenabled && anotherint) {
					passw = validate(randomgeneratornumber,replicas, passw);
					if(passw.length() == len)
						break;
				}
				boolean anotherchar = anotherLetter(maxnumerofchar, passw);
				if(isaLetter(randomgeneratornumber) && ischaracterenabled && anotherchar){
					passw = validate(randomgeneratornumber,replicas, passw);
					if(passw.length() == len)
						break;
				}
				boolean anotherspecialchar = anotherSpecialChar(maxnumerofsecialchar, passw);
				if(isaSpecialCharacter(randomgeneratornumber) && isspecialcharenabl && anotherspecialchar) {
					passw = validate(randomgeneratornumber,replicas, passw);
					if(passw.length() == len)
						break;
				}
			}
			// last time we shuffle the sequence of character that we obtained
			passw = scramble(passw);
		}
	return passw;
	}
	/**
	 * Set all the strings in Italian language
	 */
	private void italian() {
		model.getLanguage().setSavefilebuttontext("Salva su file");
		model.getLanguage().setGeneratebutton("Genera");
		model.getLanguage().setGeneratedpwdlabeltext("Password generata");
		model.getLanguage().setSpecialcharactercheckboxtext("Usa caratteri speciali");
		model.getLanguage().setLetterscheckboxtext("Usa lettere");
		model.getLanguage().setNumbercheckboxtext("Usa numeri");
		model.getLanguage().setPasswordlengthlabeltest("Lunghezza della pasword");
		model.getLanguage().setDifferentpwdradiobuttontext("Password differisce di...");
		model.getLanguage().setNumberofdiffcharlabeltext("# di caratteri differenti");
		model.getLanguage().setTitleapplabeltext("Generatore di Password ".concat(model.getLanguage().getVersion()));
		model.getLanguage().setSamenumberradiobuttontext("Stesso numero di caratterisp, numeri, lettere");
		model.getLanguage().setReplicaslabeltext("Numero di caratteri uguali");
		model.getLanguage().setOpenbuttontext("Apri");
		model.getLanguage().setInputerror("Errore inseriemnto dati");
		model.getLanguage().setTomuchdiffchar("Non puoi avere un numero di caratteri diversi che sia superiore al numero di caratteri presenti nella stringa stessa");
		model.getLanguage().setDialogtitletext("salva il file");
		model.getLanguage().setExstentiontext("Documento di testo(*.txt)");
		model.getLanguage().setFileoverwritebodytext("il file esiste già lo vuoi sovrascrivere?");
		model.getLanguage().setFileovervritetitletext("il file esiste già");
		model.getLanguage().setIotitletext("errore I/O");
		model.getLanguage().setLenexecptiontext("La lunghezza della password non può essere minore o uguale a zero");
		model.getLanguage().setSelectionexceptiontext("prego selezionare almeno una checkbox");
		model.getLanguage().setOpendialogtitle("Apri un file");
		model.getLanguage().setIobodytext("Non posso aprire questo file");
		model.getLanguage().setNofileselectedexceptionbodytext("Non è stato aperto nessun file");
		model.getLanguage().setNofileselectedexceptiontitletext("Nessun file aperto");
		model.getLanguage().setLanguage("Cambia lingua");
	}
	/**
	 * Set all the strings in English language
	 */
	private void english() {
		model.getLanguage().setSavefilebuttontext("Save on file");
		model.getLanguage().setGeneratebutton("Generate");
		model.getLanguage().setGeneratedpwdlabeltext("Generated password");
		model.getLanguage().setSpecialcharactercheckboxtext("Use special character");
		model.getLanguage().setLetterscheckboxtext("Use letters");
		model.getLanguage().setNumbercheckboxtext("Use numbers");
		model.getLanguage().setPasswordlengthlabeltest("Password length");
		model.getLanguage().setDifferentpwdradiobuttontext("Password that differ from...");
		model.getLanguage().setNumberofdiffcharlabeltext("# of different charaters");
		model.getLanguage().setTitleapplabeltext("Password Generator ".concat(model.getLanguage().getVersion()));
		model.getLanguage().setSamenumberradiobuttontext("Same number of specialchar,number,letters");
		model.getLanguage().setReplicaslabeltext("Numbers of equals character");
		model.getLanguage().setOpenbuttontext("Open");
		model.getLanguage().setInputerror("Input Error");
		model.getLanguage().setTomuchdiffchar("You cannot have a password whith more different than the total numer of character whitch is composed");
		model.getLanguage().setDialogtitletext("save a file");
		model.getLanguage().setExstentiontext("Text Documents(*.txt)");
		model.getLanguage().setFileoverwritebodytext("The file exists, overwrite?");
		model.getLanguage().setFileovervritetitletext("Existing file");
		model.getLanguage().setIotitletext("I/O Error");
		model.getLanguage().setLenexecptiontext("len cannot be less or equal than 0");
		model.getLanguage().setSelectionexceptiontext("please select at least one checkbox");
		model.getLanguage().setOpendialogtitle("open a file");
		model.getLanguage().setIobodytext("I cannot open this file");
		model.getLanguage().setNofileselectedexceptionbodytext("No file open");
		model.getLanguage().setNofileselectedexceptiontitletext("File not open");
		model.getLanguage().setLanguage("Change language");
	}
	/**
	 * This method is used to provide the image icon to the view
	 * @return the image icon corresponding to the italy flag
	 */
	public ImageIcon getTheItalyFlag() {
		return model.getItalyimageicon();
	}
	/**
	 * This method is used to provide the image icon to the view
	 * @return the image icon corresponding to the united kindom flag
	 */
	public ImageIcon getTheUkFlag() {
		return model.getUkimageicon();
	}
}
