package com.amazon.datastructures.heaps;

public class Point3D implements Comparable<Point3D> {

    int x, y, z;
    
    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int compareTo(Point3D o) {
        double dist = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        double distOther = Math.sqrt(Math.pow(o.x, 2) + Math.pow(o.y, 2) + Math.pow(o.z, 2));
        return Double.compare(dist, distOther);
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof Point3D)) {
    		return false;
    	}
    	Point3D p = (Point3D) obj;
    	
    	return this.x == p.x && this.y == p.y && this.z == p.z;
    }

}
