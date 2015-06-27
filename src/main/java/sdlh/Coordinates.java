package sdlh;

public class Coordinates {

	public long getLatitudeE7() {
		return latitudeE7;
	}

	public long getLongitudeE7() {
		return longitudeE7;
	}

	private final long latitudeE7;
	private final long longitudeE7;

	public Coordinates(long latitudeE7, long longitudeE7) {
		this.latitudeE7 = latitudeE7;
		this.longitudeE7 = longitudeE7;
	}
}
