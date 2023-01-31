package Generator;
import javax.swing.ImageIcon;

import lombok.Data;
@Data
public class Model {
	private String password;
	private Language language;
	private ImageIcon italyimageicon;
	private ImageIcon ukimageicon;
	private int inputPasswordLen;
	/**
	 * Constructor that initialize the strings
	 */
	public Model() {
		super();
		password = new String();
		inputPasswordLen = 0;
		language = new Language();
		ukimageicon = new ImageIcon(getClass().getClassLoader().getResource("united_kindom.jpg"));
		italyimageicon = new ImageIcon(getClass().getClassLoader().getResource("italy.jpg"));
	}
}
