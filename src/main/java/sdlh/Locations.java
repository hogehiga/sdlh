package sdlh;

import java.util.Collections;
import java.util.List;

public final class Locations {

	public List<Location> getLocations() {
		return Collections.unmodifiableList(locations);
	}

	private final List<Location> locations;

	public Locations(List<Location> locations) {
		this.locations = locations;
	}
}
