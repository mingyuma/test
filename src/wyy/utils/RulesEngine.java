package wyy.utils;

import android.util.Log;

public class RulesEngine {
	public static String Parse(String type, String fenli, String taxian) {
		String str = "no";

		if (type.equals("Schatzker����") && fenli.equals("<3mm")
				&& taxian.equals("<3mm")) {
			str = "��������:\nʯ����̶�/֧����̶�\n";
			str += "��������:\n1�����ǿ�С��ʹ��С�пڣ�2��3öֱ��6.5��7.0 mm�����ʹ������ݶ��ӵ�Ȧ�̶�\n"
					+ "2�������ۿ�ϴ󡢽Ϸ������ʽ����ɣ���ʹ�����֧�Ÿְ������ְ塣";
		}
		if (type.equals("Schatzker����") && fenli.equals(">3mm")
				&& taxian.equals("<3mm")) {
			str = "�������ƣ�\n1�����ǿ�С��ʹ��С�пڣ�2��3öֱ��6.5��7.0 mm�����ʹ������ݶ��ӵ�Ȧ�̶�\n"
					+ "2�������ۿ�ϴ󡢽Ϸ������ʽ����ɣ���ʹ�����֧�Ÿְ������ְ塣";
		}
		if (type.equals("Schatzker����") && fenli.equals(">3mm")
				&& taxian.equals(">3mm")) {
			str = "��������:\n1��������ʧ���ࣺǰ����пڡ��п���λ֧�Ÿְ��ڹ̶���ʹ�ö�ö�ݶ�֧�����ݵĹؽ��棬��ֲ�ǡ�\n"
					+ "2��������ʧ�ࣺǰ����пڡ��п���λ֧�Ÿְ��ڹ̶���ʹ�ö�ö�ݶ�֧�����ݵĹؽ��棬ֲ�ǡ�";
		}
		if (type.equals("Schatzker����") && fenli.equals(">3mm")) {
			str = "��������:\n�Ƽ������ڲ�С�пڡ�Ƥ�ʹǿ�����ؽھ������¸�λ��ֲ�����ʹǻ���������ݶ��̶���";
		}
		if (type.equals("Schatzker����") && fenli.equals(">3mm")) {
			str = "��������:\n�Ƽ������ڲ��пڡ��п���λ֧�Ÿְ��ڹ̶���";
		}
		if (type.equals("Schatzker����") && fenli.equals(">3mm")) {
			str = "��������:\n����������пڡ��п���λ֧�Ÿְ��ڹ̶���";
		}
		if (type.equals("Schatzker����") && fenli.equals(">3mm")) {
			str = "��������:\n��������̶����ƣ�������֯�ָ��������������������ˡ�����Ƥ����ʱ�ж����п���λ�ڹ̶���"
					+ "����������пڡ��п���λ֧�Ÿְ��ڹ̶���";
		}
		Log.i("wyy", str);
		return str;
	}
}
