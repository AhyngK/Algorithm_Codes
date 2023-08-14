import java.util.*;
public class Solution {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int testCase = sc.nextInt();
	sc.nextLine();
	
	for(int tc=1; tc<=testCase; tc++) {
		int size = sc.nextInt();

		int[][] arr = new int[size][size];

		int x = 0;
		int y = 0;
		int num = 1;

		int currentSize = size;
		int currentMin = -1;

		a:
		while (true) {
			while (true) {
				if (num == (size * size) + 1) {
					break a;
				}
				arr[x][y] = num;
				y += 1;
				num += 1;
				if (y == currentSize) {
					break;
				}
			}

			x++;
			y--;
			while (true) {
				if (num == (size * size) + 1) {
					break a;
				}
				arr[x][y] = num;
				x += 1;
				num += 1;
				if (x == currentSize) {
					break;
				}
			}

			x -= 1;
			y -= 1;
			while (true) {
				if (num == (size * size) + 1) {
					break a;
				}
				arr[x][y] = num;
				y -= 1;
				num += 1;
				if (y == currentMin) {
					break;
				}
			}

			currentMin += 1;
			x -= 1;
			y += 1;
			while (true) {
				if (num == (size * size) + 1) {
					break a;
				}
				arr[x][y] = num;
				x -= 1;
				num += 1;
				if (x == currentMin) {
					break;
				}
			}

			x++;
			y++;
			currentSize--;
		}

		System.out.println("#" + tc);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
}
