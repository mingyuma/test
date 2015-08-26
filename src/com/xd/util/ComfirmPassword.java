package com.xd.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��֤�����Ƿ�Ϸ�
 * 
 * @author Bryan
 * @version 0.1
 * @crested 2013-8-2
 */
public class ComfirmPassword {
	private static String regex = "^[\\w_]{6,}+$";
	private static String numRegex = "[0-9]";
	private static String chRegex = "[a-zA-Z]";

	public static boolean isLegal(String password) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			return false;
		}
		pattern = Pattern.compile(numRegex);
		matcher = pattern.matcher(password);

		pattern = Pattern.compile(chRegex);
		matcher = pattern.matcher(password);

		return (matcher.find() && matcher.find());
	}

	public static boolean isLegal2(String password) {
		// �����Ƿ�����ĸ�������ֵ���ϣ�ͬʱ�ַ���Ҫ���ڻ��ߵ���9
		boolean b1 = Pattern.compile("^[A-Za-z0-9]{6,}$").matcher(password).find();
		// �����Ƿ�ȫ������
		boolean b2 = Pattern.compile("^[0-9]*$").matcher(password).find();
		// �����Ƿ�ȫ����ĸ
		boolean b3 = Pattern.compile("^[A-Za-z]+$").matcher(password).find();

		// System.out.println("b1=" + b1 + ";b2=" + b2 + ";b3=" + b3);

		// ��������ַ�����ĸ�����ֵ���϶��Ҵ��ڻ��ߵ���9λʱ��ȷ
		if (b1 && !b2 && !b3) {
			return true;
		} else {
			return false;
		}
	}
}
