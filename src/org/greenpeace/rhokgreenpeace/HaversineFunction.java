package org.greenpeace.rhokgreenpeace;
import static java.lang.Math.*;

public class HaversineFunction {
	/* The haversine algorithm, for calculating the distance between two points on a sphere.
	 * Takes the longitude and the latitude of the two points in decimal degree values, and 
	 * computes the distance in kilometres, this is useful for deciding whether or not people 
	 * should be notified with a push. */
	public double haversine(double lon1, double lat1, double lon2, double lat2) {
		lon1 = toRadians(lon1);
		lat1 = toRadians(lat1);
		lon2 = toRadians(lon2);
		lat2 = toRadians(lat2);
		double d = acos(sin(lat1)*sin(lat2) + cos(lat1)*cos(lat2)
							*cos(lon2-lon1))*6371.0;
		return d;
	}
}
