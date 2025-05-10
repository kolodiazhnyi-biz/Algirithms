/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *
 * Dutch national flag. Given an array of n buckets, each containing a red, white, or blue pebble,
 * sort them by color. The allowed operations are: swap(i,j) swap the pebble in bucket i with
 * the pebble in bucket j. color(i): determine the color of the pebble in bucket i.
 * The performance requirements are as follows: At most n calls to color(). At most n calls to
 * swap. Constant extra space.
 *
 **************************************************************************** */

public class DutchFlag {

    static class Pebble {
        int color; // 0 for red, 1 for white, 2 for blue

        public Pebble(int color) {
            this.color = color;
        }
    }

    public static void sortPebbles(Pebble[] pebbles) {
        int low = 0, mid = 0, high = pebbles.length - 1;

        while (mid <= high) {
            if (pebbles[mid].color == 0) { // red
                swap(pebbles, low, mid);
                low++;
                mid++;
            }
            else if (pebbles[mid].color == 1) { // white
                mid++;
            }
            else { // blue
                swap(pebbles, mid, high);
                high--;
            }
        }
    }

    private static void swap(Pebble[] pebbles, int i, int j) {
        Pebble temp = pebbles[i];
        pebbles[i] = pebbles[j];
        pebbles[j] = temp;
    }

    public static void main(String[] args) {
        Pebble[] pebbles = {
                new Pebble(0), // red
                new Pebble(1), // white
                new Pebble(2), // blue
                new Pebble(0), // red
                new Pebble(1), // white
                new Pebble(2)  // blue
        };

        sortPebbles(pebbles);

        for (Pebble pebble : pebbles) {
            System.out.print(pebble.color + " ");
        }
    }
}
