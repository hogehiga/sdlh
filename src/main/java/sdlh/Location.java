package sdlh;

public final class Location {
	public long getTimestampMs() {
		return timestampMs;
	}

	public long getLatitudeE7() {
		return latitudeE7;
	}

	public long getLongitudeE7() {
		return longitudeE7;
	}

	private final long timestampMs;
	private final long latitudeE7;
	private final long longitudeE7;

	public Location(long timestampMs, long latitudeE7, long longitudeE7) {
		this.timestampMs = timestampMs;
		this.latitudeE7 = latitudeE7;
		this.longitudeE7 = longitudeE7;
	}
}
