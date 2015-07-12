
/**
*
* Class "ClosestPair" implements closest pair algorithm
*
* @version 1.0
*
* @author  Manas Mandhani
*/
public class ClosestPair {
	Point[] Px, Py, Q, R, Qx, Qy, Rx, Ry; // Points within a plain.
	MergeSort msort;
	float d;

	public ClosestPair(Point[] point) { 
		Px = new Point[point.length];
		Py = new Point[point.length];
		msort = new MergeSort();
		d = 0;
	}

	/**
	* Sort points according to "x" and "y" co-ordinates
	*/
	public void SortedPoints(Point[] point) {
		msort.mergeSort(point, true);
		for (int i = 0; i < point.length; i++) {
			Px[i] = point[i];
		}

		msort.mergeSort(point, false);
		for (int i = 0; i < point.length; i++) {
			Py[i] = point[i];
		}
	}

	/**
	* Distance between points
	*/
	public double PointDistance(Point a, Point b) {
		float d1 = a.x - b.x;
		if (d1 < 0)
			d1 = d1 * (-1);
		float d2 = a.y - b.y;
		if (d2 < 0)
			d2 = d2 * (-1);
	float d =(float) Math.sqrt((d1)*(d1) + (d2)*(d2));
		//float d = (float) Math.pow((Math.pow(d1, 2) + Math.pow(d2, 2)), 0.5);
		return d;
	}

	/**
	*
	* Finding closes points.
	*/
	public Point[] ClosestPoints(Point[] Px, Point[] Py) {
		if (Px.length <= 3) {
			Point[] pq = new Point[2];
			float min = 2139999999;
			for (int i = 0; i < Px.length; i++) {
				for (int j = i + 1; j < Px.length; j++) {
					try{
						d = (float) this.PointDistance(Px[i], Px[j]);
					}catch(Exception e){
						System.out.println("Number of digits a data value exceeds the max value ");
						System.out.println("Please input any length of data with each value having proper number of digits. ");
					System.exit(0);
					}
					if (d < min) {
						min = d;
						int count = 0;
						pq[count] = Px[i];
						count++;
						pq[count] = Px[j];
					}
				}
			}
			return pq;
		}

		int n = Px.length;
		int middle = n / 2;
		Q = new Point[middle];
		R = new Point[n - middle];

		for (int i = 0; i < middle; i++) {
			Q[i] = Px[i];
		}

		for (int i = middle; i < Px.length; i++) {
			R[i - middle] = Px[i];
		}

		Qx = new Point[Q.length];
		msort.mergeSort(Q, true);
		for (int i = 0; i < Q.length; i++) {
			Qx[i] = Q[i];
		}

		Qy = new Point[Q.length];
		msort.mergeSort(Q, false);
		for (int i = 0; i < Q.length; i++) {
			Qy[i] = Q[i];
		}

		Rx = new Point[R.length];
		msort.mergeSort(R, true);
		for (int i = 0; i < R.length; i++) {
			Rx[i] = R[i];
		}

		Ry = new Point[R.length];
		msort.mergeSort(R, false);
		for (int i = 0; i < R.length; i++) {
			Ry[i] = R[i];
		}

		Point[] pair1 = new Point[2];
		Point[] pair2 = new Point[2];

		pair1 = ClosestPoints(Qx, Qy);
		pair2 = ClosestPoints(Rx, Ry);

		Point[] MinPointPair;
		if (this.PointDistance(pair1[0], pair1[1]) < this.PointDistance(
				pair2[0], pair2[1]))
			MinPointPair = pair1;
		else
			MinPointPair = pair2;

		int size = Qx.length - 1;
		int counter = 0;
		for (int i = size; i > 0; i--) {
			if (this.PointDistance(Qx[i], Qx[size]) < this.PointDistance(
					MinPointPair[0], MinPointPair[1])) {
				counter++;
			}
		}

		for (int i = 0; i < Rx.length; i++) {
			if (this.PointDistance(Rx[i], Qx[size]) < this.PointDistance(
					MinPointPair[0], MinPointPair[1])) {
				counter++;
			}
		}

		if (counter == 0)
			return MinPointPair;
		else {
			Point[] S = new Point[counter];
			int counter1 = 0;
			for (int i = size; i >= 0; i--) {
				if (this.PointDistance(Qx[i], Qx[size]) < this.PointDistance(
						MinPointPair[0], MinPointPair[1])) {
					S[counter1] = Qx[i];
					counter1++;
				}
			}

			for (int i = 0; i < Rx.length; i++) {
				if (this.PointDistance(Rx[i], Qx[size]) < this.PointDistance(
						MinPointPair[0], MinPointPair[1])) {
					S[counter1] = Rx[i];
					counter1++;
				}
			}
			Point[] Sy = new Point[S.length];
			msort.mergeSort(S, false);
			for (int i = 0; i < S.length; i++) {
				Sy[i] = S[i];
			}

			if (Sy.length > 1) {
				Point[] f = new Point[2];
				float dist = 0;
				float min_dist = 2139999999;
				int check = 15;
				if (Sy.length < 15) {
					check = Sy.length;
				}

				for (int i = 0; i < check; i++) {
					for (int j = i + 1; j < check; j++) {
						dist = (float) this.PointDistance(Sy[i], Sy[j]);
						if (dist < min_dist) {
							min_dist = dist;
							int c = 0;
							f[c] = Sy[i];
							c++;
							f[c] = Sy[j];
						}
					}
				}
				if (this.PointDistance(f[0], f[1]) < this.PointDistance(
						MinPointPair[0], MinPointPair[1])) {
					return f;
				} else {
					return MinPointPair;
				}
			} else
				return MinPointPair;
		}
	}

	public void Print(Point[] p) {
		System.out.println("Closest Pair of Points are: " + "(" + p[0].x + ","
				+ p[0].y + ")" + " & " + "(" + p[1].x + "," + p[1].y + ")");
	}

	public static void main(String[] args) {
		int i = 0;
		int count = 0;
		int n = args.length;
		if (n%2!=0){
			n = n - 1;
		}
		Point[] point = new Point[n / 2];
		while (i < n) {
			point[count] = new Point(Integer.parseInt(args[i]),
					Integer.parseInt(args[++i]));
			i++;
			count++;
		}
		ClosestPair ob = new ClosestPair(point);
		ob.SortedPoints(point);
		Point[] p = ob.ClosestPoints(ob.Px, ob.Py);
		ob.Print(p);
		}
	}


