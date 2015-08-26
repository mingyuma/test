package passenger.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class DiseaseXmlParser {
	public static List<Disease> DiseasexmlParser(InputStream in)
			throws Exception {
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(in, "gbk");
		int eventType = parser.getEventType();

		Disease disease = null;
		List<Disease> diseases = new ArrayList<Disease>();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("disease")) {
					disease = new Disease();
				} else if (parser.getName().equals("name")) {
					disease.setName(parser.nextText());
				} else if (parser.getName().equals("nikcname")) {
					disease.setNickName(parser.nextText());
				} else if (parser.getName().equals("cheifContent")) {
					disease.setChiefContent(parser.nextText());
				} else if (parser.getName().equals("perform")) {
					disease.setPerforms(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("disease")) {
					diseases.add(disease);
					disease = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return diseases;
	}
}
