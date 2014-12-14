package pri.adam.dmail.utils.normalSec;


import org.junit.Test;
import pri.adam.dmail.utils.security.normalSec.EncryptImpl.ConsoleInfoEncrypter;
import pri.adam.dmail.utils.security.normalSec.EncryptManager;
import pri.adam.dmail.utils.security.normalSec.EncryptStrategy;
import pri.adam.dmail.utils.security.normalSec.StrategyImpl.S_MD5;

import java.io.UnsupportedEncodingException;


public class TestEncrpter {

	/**
	 * 2014-11-16
	 * ����ͨ���÷��������е����ļ���
	 */
	@Test
	public void test() throws UnsupportedEncodingException {
		String password = "adam";
		EncryptManager encryptManager = new EncryptManager();
		encryptManager.setEncryter(new ConsoleInfoEncrypter());
		EncryptStrategy encryptStrategy = new S_MD5();
		encryptManager.setEncryptStrategy(encryptStrategy);
		String encryptPW = encryptManager.encrypt(password);
		System.out.println("���ܺ������Ϊ : "+encryptPW);
	}

}
