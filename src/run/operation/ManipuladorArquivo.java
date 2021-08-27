package run.operation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ManipuladorArquivo {

	 
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		Sessao sessao = Sessao.getInstance();
		FileInputStream file;
		
		if(sessao.getLanguage() == "PT") {
			file = new FileInputStream(
					"Language/pt-br.properties");
		}else {
			file = new FileInputStream(
					"Language/en.properties");
		}
		
		props.load(file);
		return props;
	}

	public static String[] getTextFrame() throws IOException {
		String[] text = new String[19];

		Properties prop = getProp();
		
		text[0] = prop.getProperty("props.frame.1");
		text[1] = prop.getProperty("props.frame.2");
		text[2] = prop.getProperty("props.frame.3");
		text[3] = prop.getProperty("props.frame.4");
		text[4] = prop.getProperty("props.frame.5");
		text[5] = prop.getProperty("props.frame.6");
		text[6] = prop.getProperty("props.frame.7");
		text[7] = prop.getProperty("props.frame.8");
		text[8] = prop.getProperty("props.frame.9");
		text[9] = prop.getProperty("props.frame.10");
		text[10] = prop.getProperty("props.frame.11");
		text[11] = prop.getProperty("props.frame.12");
		text[12] = prop.getProperty("props.frame.13");
		text[13] = prop.getProperty("props.frame.14");
		text[14] = prop.getProperty("props.frame.15");
		text[15] = prop.getProperty("props.frame.16");
		text[16] = prop.getProperty("props.frame.17");
		text[17] = prop.getProperty("props.frame.18");
		text[18] = prop.getProperty("props.frame.19");
		
		return text;
	}
	
	public static String[] getTextResetMemory() throws IOException {
		String[] text = new String[5];

		Properties prop = getProp();
		
		text[0] = prop.getProperty("props.resetMemory.1");
		text[1] = prop.getProperty("props.resetMemory.2");
		text[2] = prop.getProperty("props.resetMemory.3");
		text[3] = prop.getProperty("props.resetMemory.4");
		text[4] = prop.getProperty("props.resetMemory.5");
		
		return text;
	}
	
	public static String[] getTextProfile() throws IOException {
		String[] text = new String[8];

		Properties prop = getProp();
		
		text[0] = prop.getProperty("props.profile.1");
		text[1] = prop.getProperty("props.profile.1");
		text[2] = prop.getProperty("props.profile.3");
		text[3] = prop.getProperty("props.profile.4");
		text[4] = prop.getProperty("props.profile.5");
		text[5] = prop.getProperty("props.profile.6");
		text[6] = prop.getProperty("props.profile.7");
		text[7] = prop.getProperty("props.profile.8");
		
		return text;
	}
	
	public static String[] getTextAbout() throws IOException {
		String[] text = new String[2];

		Properties prop = getProp();
		
		text[0] = prop.getProperty("props.about.1");
		text[1] = prop.getProperty("props.about.1");

		
		return text;
	}
	
	
}
