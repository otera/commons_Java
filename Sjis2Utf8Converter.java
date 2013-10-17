import java.util.ArrayList;
import java.util.List;

/***
 * S-JISからUTF-8へ変換します。
 */
public class Sjis2Utf8Converter {

	/***
	 * S-JISからUTF-8へ変換します。
	 *
	 * sjisがnullの場合、0バイト文字列を返します。
	 * sjisが0バイト文字列の場合、0バイト文字列を返します。
	 *
	 * @param sjis S-JISの文字列
	 * @return UTF-8の文字列
	 */
	public static String convert(String sjis) {

		if (null == sjis) {
			return "";
		}

		if ("".equals(sjis)) {
			return "";
		}

		StringBuilder utf8 = new StringBuilder();
		char chr;

		for (int i = 0; i < sjis.length(); i++) {
			chr = sjis.charAt(i);

			switch (chr) {
			case 0x2014:
				// 「―」のとき
				chr = 0x2015;
				break;

			case 0x301c:
				// 「～」のとき
				chr = 0xff5e;
				break;

			case 0x2212:
				// 「－」のとき
				chr = 0xff0d;
				break;

			default:
			}

			utf8.append(chr);
		}

		return utf8.toString();
	}

	/**
	 * 文字列を16進数表現にした配列を取得します。
	 * （例：0x301c）
	 *
	 * @param str 文字列
	 * @return 1文字ずつ16進数表現にした配列
	 */
	public static String[] getHexString(String str) {
		char chr;
		String hexString;
		List<String> hexStrings = new ArrayList<String>();

		for (int i = 0; i < str.length(); i++) {
			chr = str.charAt(i);
			hexString = "0x" + Integer.toHexString((int) chr);
			hexStrings.add(hexString);
		}

		return (String[]) hexStrings.toArray(new String[0]);
	}

}
