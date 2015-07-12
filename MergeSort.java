
/**
*
* Class "MergeSort" implements merge sort.
*
* @version 1.0
*
* @author  Manas Mandhani
*/

public class MergeSort {
	public void mergeSort(Point[] array, boolean x_coordinate) {
		int n = array.length;
		int counter = 0;
		if (n < 2)
			return;
		int middle = n / 2;
		Point[] left_array = new Point[middle];
		Point[] right_array = new Point[n - middle];
		for (int i = 0; i < middle; i++) {
			left_array[i] = array[i];
		}
		for (int i = middle; i < n; i++) {
			right_array[counter] = array[i];
			counter++;
		}
		mergeSort(left_array, x_coordinate);
		mergeSort(right_array, x_coordinate);
		Merge(left_array, right_array, array, x_coordinate);
	}

	public void Merge(Point[] left_array, Point[] right_array, Point[] array,
			boolean x_coordinate) {
		int left, right, count;
		if (x_coordinate) {
			for (left = 0, right = 0, count = 0; left < left_array.length
					&& right < right_array.length; count++) {
				if (left_array[left].x < right_array[right].x) {
					array[count] = left_array[left];
					left++;
				} else {
					array[count] = right_array[right];
					right++;
				}
			}

			for (; left < left_array.length; left++, count++) {
				array[count] = left_array[left];
			}

			for (; right < right_array.length; right++, count++) {
				array[count] = right_array[right];
			}
		} else {
			for (left = 0, right = 0, count = 0; left < left_array.length
					&& right < right_array.length; count++) {
				if (left_array[left].y < right_array[right].y) {
					array[count] = left_array[left];
					left++;
				} else {
					array[count] = right_array[right];
					right++;
				}
			}

			for (; left < left_array.length; left++, count++) {
				array[count] = left_array[left];
			}
			for (; right < right_array.length; right++, count++) {
				array[count] = right_array[right];
			}
		}
	}
}
