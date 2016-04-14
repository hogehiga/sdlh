package sdlh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		if (args.length != 5) {
			System.err.println(String.format("usage: %s latitudeOfUpperLeft longitudeOfUpperLeft latitudeOfLowerRight longitudeOfLowerRight location-history.json",
					System.getProperty("java.class.path")));
			System.exit(1);
		}
		final Coordinates UL = new Coordinates(new Double(Double.parseDouble(args[0]) * Math.pow(10, 7)).longValue(), new Double(Double.parseDouble(args[1]) * Math.pow(10, 7)).longValue());
		final Coordinates LR = new Coordinates(new Double(Double.parseDouble(args[2]) * Math.pow(10, 7)).longValue(), new Double(Double.parseDouble(args[3]) * Math.pow(10, 7)).longValue());
		final Set<String> dates = getDatesWhichHaveCoordinatesInRange(UL, LR, args[4]);
		for (String date : dates) {
			System.out.println(date);
		}
	}

	/**
	 * Googleロケーション履歴の座標が指定した長方形の範囲内にある時、その座標が記録された日付の集合を返す。
	 *
	 * @param UL
	 *            範囲指定(左上)。
	 * @param LR
	 *            範囲指定(右下)。
	 * @param filename
	 *            ロケーション履歴.jsonのパス。
	 * @return 指定された長方形の範囲内に座標があった日付の集合。
	 * @throws IOException
	 *             IOエラー
	 */
	static Set<String> getDatesWhichHaveCoordinatesInRange(Coordinates UL, Coordinates LR, String filename) throws IOException {
		final Set<String> dates = new TreeSet<String>();
		final Gson g = new Gson();
		final Locations locations = g.fromJson(new BufferedReader(new FileReader(filename)), Locations.class);

		for (Location location : locations.getLocations()) {
			final Coordinates c = new Coordinates(location.getLatitudeE7(), location.getLongitudeE7());
			if (isInRange(UL, LR, c)) {
				dates.add(millSecondsToDate(location.getTimestampMs()));
			}
		}
		return Collections.unmodifiableSet(dates);
	}

	private static boolean isInRange(Coordinates UL, Coordinates LR, Coordinates p) {
		if (UL.getLongitudeE7() > LR.getLongitudeE7()) {
			final Coordinates tUL = new Coordinates(UL.getLatitudeE7(), -1800000000L);
			final Coordinates tLR = new Coordinates(LR.getLatitudeE7(), 1800000000L);
			return isInRange(UL, tLR, p) || isInRange(tUL, LR, p);
		} else if (UL.getLatitudeE7() < LR.getLatitudeE7()) {
			return isInRange(LR, UL, p);
		} else if (between(LR.getLatitudeE7(), UL.getLatitudeE7(), p.getLatitudeE7()) && between(UL.getLongitudeE7(), LR.getLongitudeE7(), p.getLongitudeE7())) {
			return true;
		}
		return false;
	}

	private static boolean between(long lower, long upper, long t) {
		if (lower <= t && t <= upper) {
			return true;
		}
		return false;
	}

	private static String millSecondsToDate(long ms) {
		final ZonedDateTime d = ZonedDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneId.systemDefault());
		final DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		return d.format(dtf);
	}
}
