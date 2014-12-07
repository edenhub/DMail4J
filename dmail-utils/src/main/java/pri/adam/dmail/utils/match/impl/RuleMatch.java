package pri.adam.dmail.utils.match.impl;

import pri.adam.dmail.utils.match.ISingleRuleMatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ����������ʽʵ���ֶε��ж�
 * @author adam
 *
 */
public class RuleMatch implements ISingleRuleMatch {
	protected Pattern pattern;
	private Matcher matcher;
	
	public RuleMatch(){
		pattern = Pattern.compile("");
	}
	
	public RuleMatch(String rule){
		assert rule!=null;
		assert rule!="";
		pattern = Pattern.compile(rule);
	}

	@Override
	public boolean isMatch(String src) {
		if(BaseElementMatch.matchStringNotBlank(src)){
			matcher = pattern.matcher(src);
			return matcher.matches();
		}
		return false;
	}

	@Override
	public void updateRule(String rule) {
		pattern = Pattern.compile(rule);
	}
	
	
}
