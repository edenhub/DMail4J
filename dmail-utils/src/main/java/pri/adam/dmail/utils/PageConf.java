package pri.adam.dmail.utils;

import java.io.IOException;
import java.util.Properties;

public class PageConf {
//	private static final String confPath = "src/pageconf.properties";
	private static Properties confProperties = new Properties();
	private static PageConf instance;
	
	private PageConf(){
		try{
			confProperties.load(Version.class.getResourceAsStream("../../../../pageconf.properties"));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getRuleProperty(String name){
		return confProperties.get(name).toString();
	}
	
	/*public String[] getTypeProperty(String name){
		String valueString = confProperties.getProperty(name);
		return valueString.split(",");
	}*/
	
	public static PageConf getInstance(){
		if(instance==null)
			instance = new PageConf();
		return instance;
	}
}
