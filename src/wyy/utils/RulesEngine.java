package wyy.utils;

import android.util.Log;

public class RulesEngine {
	public static String Parse(String type, String fenli, String taxian) {
		String str = "no";

		if (type.equals("SchatzkerⅠ型") && fenli.equals("<3mm")
				&& taxian.equals("<3mm")) {
			str = "保守治疗:\n石膏外固定/支具外固定\n";
			str += "手术治疗:\n1、外侧骨块小，使用小切口，2～3枚直径6.5或7.0 mm的松质骨拉力螺钉加垫圈固定\n"
					+ "2、外侧骨折块较大、较粉碎或骨质较疏松，则使用外侧支撑钢板或防滑钢板。";
		}
		if (type.equals("SchatzkerⅠ型") && fenli.equals(">3mm")
				&& taxian.equals("<3mm")) {
			str = "手术治疗：\n1、外侧骨块小，使用小切口，2～3枚直径6.5或7.0 mm的松质骨拉力螺钉加垫圈固定\n"
					+ "2、外侧骨折块较大、较粉碎或骨质较疏松，则使用外侧支撑钢板或防滑钢板。";
		}
		if (type.equals("SchatzkerⅡ型") && fenli.equals(">3mm")
				&& taxian.equals(">3mm")) {
			str = "手术治疗:\n1、骨量丢失不多：前外侧切口、切开复位支撑钢板内固定，使用多枚螺钉支撑塌陷的关节面，不植骨。\n"
					+ "2、骨量丢失多：前外侧切口、切开复位支撑钢板内固定，使用多枚螺钉支撑塌陷的关节面，植骨。";
		}
		if (type.equals("SchatzkerⅢ型") && fenli.equals(">3mm")) {
			str = "手术治疗:\n推荐采用内侧小切口、皮质骨开窗或关节镜辅助下复位，植入松质骨或骨替代物后螺钉固定。";
		}
		if (type.equals("SchatzkerⅣ型") && fenli.equals(">3mm")) {
			str = "手术治疗:\n推荐采用内侧切口、切开复位支撑钢板内固定。";
		}
		if (type.equals("SchatzkerⅤ型") && fenli.equals(">3mm")) {
			str = "手术治疗:\n内外侧联合切口、切开复位支撑钢板内固定。";
		}
		if (type.equals("SchatzkerⅥ型") && fenli.equals(">3mm")) {
			str = "手术治疗:\n早期行外固定治疗，给软组织恢复创造条件，待肿胀消退、出现皮纹征时行二期切开复位内固定。"
					+ "内外侧联合切口、切开复位支撑钢板内固定。";
		}
		Log.i("wyy", str);
		return str;
	}
}
