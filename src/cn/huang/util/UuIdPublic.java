package util;

import java.util.Date;


public class UuIdPublic {
	public static String timeUUID() {
		// UUID.randomUUID().toString()
		String uuid = String.valueOf(new Date().getTime());
		return uuid;
	}

	public static String timeID(int random) {
		// UUID.randomUUID().toString()
		String uuid = DateUtils.date2Str(new Date(), DateUtils.yyyymmddhhmmssSSS()) + StringUtil.numRandom(random);
		return uuid;
	}

	public static String timeStrongID(int random) {
		// UUID.randomUUID().toString()
		String uuid = DateUtils.date2Str(new Date(), DateUtils.yyyymmddhhmmssSSS()) + StringUtil.strRandom(random);
		return uuid;
	}
}
