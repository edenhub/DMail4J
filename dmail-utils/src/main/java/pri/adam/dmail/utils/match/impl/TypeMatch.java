package pri.adam.dmail.utils.match.impl;

import pri.adam.dmail.utils.match.ISingleTypeMatch;

import java.util.Arrays;


public class TypeMatch implements ISingleTypeMatch {
	protected String[] types;

	@Override
	public void updateType(String... types) {
		this.types = types;
		System.out.println(Arrays.toString(types));
	}

	@Override
	public boolean isMatch(String src) {
		for(String t : types)
			if(t.equals(src))
				return true;
		return false;
	}

}
