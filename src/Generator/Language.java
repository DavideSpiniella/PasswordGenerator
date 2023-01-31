package Generator;

import lombok.Data;

@Data
public class Language {
	// text of buttons and layers
	private String savefilebuttontext;
	private String generatebutton;
	private String generatedpwdlabeltext;
	private String specialcharactercheckboxtext;
	private String letterscheckboxtext;
	private String numbercheckboxtext;
	private String passwordlengthlabeltest;
	private String differentpwdradiobuttontext;
	private String numberofdiffcharlabeltext;
	private String titleapplabeltext;
	private String samenumberradiobuttontext;
	private String replicaslabeltext;
	private String openbuttontext;
	private String version;
	private String inputerror;
	private String tomuchdiffchar;
	private String dialogtitletext;
	private String exstentiontext;
	private String fileoverwritebodytext;
	private String fileovervritetitletext;
	private String iotitletext;
	private String iobodytext;
	private String lenexecptiontext;
	private String selectionexceptiontext;
	private String opendialogtitle;
	private String nofileselectedexceptionbodytext;
	private String nofileselectedexceptiontitletext;
	private String language;
	/**
	 * Constructor, default language is English
	 */
	public Language() {
		super();
		version = "v 1.2";
		savefilebuttontext = "Save on file";
		generatebutton = "Generate";
		generatedpwdlabeltext = "Generated password";
		specialcharactercheckboxtext = "Use special character";
		letterscheckboxtext = "Use letters";
		numbercheckboxtext = "Use numbers";
		passwordlengthlabeltest =  "Password length";
		differentpwdradiobuttontext = "Password that differ from...";
		numberofdiffcharlabeltext = "# of different charaters";
		titleapplabeltext = "Password Generator";
		titleapplabeltext = titleapplabeltext.concat(" ").concat(version);
		samenumberradiobuttontext = "Same number of specialchar,number,letters";
		replicaslabeltext = "Numbers of equals character";
		openbuttontext = "Open";
		inputerror = "Input Error";
		tomuchdiffchar = "You cannot have a password whith more different than the total numer of character whitch is composed";
		dialogtitletext="save a file";
		exstentiontext="Text Documents(*.txt)";
		fileoverwritebodytext = "The file exists, overwrite?";
		fileovervritetitletext = "Existing file";
		iotitletext = "I/O Error";
		lenexecptiontext = "len cannot be less or equal than 0";
		selectionexceptiontext = "please select at least one checkbox";
		opendialogtitle = "open a file";
		iobodytext = "I cannot open this file";
		nofileselectedexceptionbodytext = "No file open";
		nofileselectedexceptiontitletext = "file not open";
		language = "Change language";
	}
}
