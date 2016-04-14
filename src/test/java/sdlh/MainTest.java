package sdlh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Set;

import org.junit.Test;

public class MainTest {

	@Test
	public void testGetDatesWhichHaveCoordinatesInRange() throws Exception {
		Locale.setDefault(Locale.ENGLISH);

		Coordinates UL = new Coordinates(354057101L, 1394631999L);
		Coordinates LR = new Coordinates(354057099L, 1394632001L);
		final String filename = "src/test/resources/testData.json";
		Set<String> s = Main.getDatesWhichHaveCoordinatesInRange(UL, LR, filename);
		assertEquals(1, s.size());
		assertTrue(s.contains("Sunday, February 22, 2015"));

		UL = new Coordinates(354057102L, 1394632000L);
		LR = new Coordinates(354057101L, 1394632001L);
		s = Main.getDatesWhichHaveCoordinatesInRange(UL, LR, filename);
		assertEquals(0, s.size());

		UL = new Coordinates(354131600L, 1394632000L);
		LR = new Coordinates(354057100L, 1394705600L);
		s = Main.getDatesWhichHaveCoordinatesInRange(UL, LR, filename);
		assertEquals(2, s.size());
		assertTrue(s.contains("Sunday, February 22, 2015"));
		assertTrue(s.contains("Monday, February 23, 2015"));

		// 経度180度線を跨ぐ場合
		UL = new Coordinates(400000000L, 1780000000L);
		LR = new Coordinates(300000000L, -1780000000L);
		s = Main.getDatesWhichHaveCoordinatesInRange(UL, LR, filename);
		assertEquals(2, s.size());
		assertTrue(s.contains("Friday, February 20, 2015"));
		assertTrue(s.contains("Saturday, February 21, 2015"));

		// 右上と左下が逆の場合
		LR = new Coordinates(354131600L, 1394632000L);
		UL = new Coordinates(354057100L, 1394705600L);
		s = Main.getDatesWhichHaveCoordinatesInRange(UL, LR, filename);
		assertEquals(2, s.size());
		assertTrue(s.contains("Sunday, February 22, 2015"));
		assertTrue(s.contains("Monday, February 23, 2015"));
	}

	@Test
	public void testGetDatesWhichHaveCoordinatesInRange_formatDependsOnLocale() throws Exception {
		Locale.setDefault(Locale.ENGLISH);

		final Coordinates UL = new Coordinates(354057101L, 1394631999L);
		final Coordinates LR = new Coordinates(354057099L, 1394632001L);
		final String filename = "src/test/resources/testData.json";
		Set<String> s = Main.getDatesWhichHaveCoordinatesInRange(UL, LR, filename);
		assertTrue(s.contains("Sunday, February 22, 2015"));

		Locale.setDefault(Locale.JAPANESE);
		s = Main.getDatesWhichHaveCoordinatesInRange(UL, LR, filename);
		assertTrue(s.contains("2015年2月22日"));
	}
}
