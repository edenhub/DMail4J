package pri.adam.dmail.utils.match;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import pri.adam.dmail.utils.PageConf;
import pri.adam.dmail.utils.Version;
import pri.adam.dmail.utils.match.impl.RuleMatch;


/**
 * �������ݶε���֤����
 * @author adam 2014-10-31
 *
 */
public class TestMatch {
	private String rultString;
	private PageConf pageConf = PageConf.getInstance();

	@Test
	public void baseMailTest() {
		rultString = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
					 "[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|" +
					 "[0-9]{1,3})(\\]?)$";
		Pattern pattern = Pattern.compile(rultString);
		Matcher matcher = pattern.matcher("cxd_dan@126.com");
		assertEquals(true, matcher.matches());
		Matcher matcherFault = pattern.matcher("asdfasdfa");
		assertEquals(false,matcherFault.matches());
	}
	
	@Test
	public void basePhoneTest(){
		rultString = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		Pattern pattern = Pattern.compile(rultString);
		Matcher matcher = pattern.matcher("15876584571");
		assertEquals(true, matcher.matches());
		Matcher matcherFault = pattern.matcher("1111111111");
		assertEquals(false, matcherFault.matches());
	}
	
	@Test
	public void basePassWordTest(){
		//����ĸ��ͷ��������6~18֮�䣬ֻ�ܰ����ַ������ֺ��»���
		rultString = "^[a-zA-Z]\\w{5,17}$";
		Pattern pattern = Pattern.compile(rultString);
		Matcher matcher = pattern.matcher("adfa__8283828");
		assertTrue(matcher.matches());
		Matcher matcherFault = pattern.matcher("asdf&&&^^^^^^$$");
		assertFalse(matcherFault.matches());
	}
	
	@Test
	public void testRuleMatch() throws IOException{
		Properties confProperties = new Properties();
		confProperties.load(Version.class.getResourceAsStream("../../../../pageconf.properties"));
		
		ISingleRuleMatch ruleMatch = new RuleMatch();
		ruleMatch.updateRule(confProperties.getProperty("rule.mail"));
		assertTrue(ruleMatch.isMatch("cxd_dan@126.com"));
		assertFalse(ruleMatch.isMatch("adfadfa"));
		ruleMatch.updateRule(confProperties.getProperty("rule.phone"));
		assertTrue(ruleMatch.isMatch("15876584571"));
		assertFalse(ruleMatch.isMatch("1111111111"));
		ruleMatch.updateRule(confProperties.getProperty("rule.password"));
		assertTrue(ruleMatch.isMatch("aaf__232__a"));
		assertFalse(ruleMatch.isMatch("9999afadfadsfafa"));
	}

	@Test
	public void testPageConf(){
		ISingleRuleMatch ruleMatch = new RuleMatch();
		ruleMatch.updateRule(pageConf.getRuleProperty("rule.mail"));
		assertTrue(ruleMatch.isMatch("cxd_dan@126.com"));
	}
	
	@Test 
	public void testTypeMatch(){
		/*ISingleTypeMatch typeMatch = new TypeMatch();
		typeMatch.updateType(pageConf.getTypeProperty("type.user"));
		assertTrue(typeMatch.isMatch("�û�"));
		assertFalse(typeMatch.isMatch("����Ͱ�"));*/
	}

}
