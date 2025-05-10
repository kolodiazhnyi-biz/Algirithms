/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 ****************************************************************************
 *
 * Egg drop. Suppose that you have an n-story building (with floors 1 through) and plenty of eggs.
 * An egg breaks if it is dropped from floor T or higher and does not break otherwise.
 * Your goal is to devise a strategy to determine the value of T given the following limitations on
 * the number of eggs and tosses:
 *
 * Version 0: 1 egg, T is less than or equal to, T tosses.
 * Version 1: ∼1lgn, n eggs and lg, n tosses.
 * Version 2: ∼lgT, lg, T eggs and 2, lg, T tosses.
 * Version 3: 2 eggs and ∼2n, 2, square root of, n, tosses.
 * Version 4: 2 eggs and lesss than or equal to, c, square root of, T, end square root tosses for some fixed constant
 *
 * */

public class EggDropVersion3 {

    private int n;
    private int f;

    public EggDropVersion3(int n, int f) {
        this.n = n;
        this.f = f;
    }

    private int eggDrop() {
        int firstEggTossFloor = (int) Math.sqrt(n);
        int secondEggTossFloor = 1;
        int tosses = 1;
        while (firstEggTossFloor < f) {
            if (firstEggTossFloor == n) {
                System.out.println("The egg broke at floor " + firstEggTossFloor);
                return tosses;
            }
            secondEggTossFloor = firstEggTossFloor;
            firstEggTossFloor += (int) Math.sqrt(n - firstEggTossFloor) + 1;
            tosses++;
        }
        while (secondEggTossFloor <= f) {
            if (secondEggTossFloor == f) {
                System.out.println("The egg broke at floor " + secondEggTossFloor);
                return tosses;
            }
            tosses++;
            secondEggTossFloor++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new EggDropVersion3(100, 1).eggDrop());
        System.out.println(new EggDropVersion3(100, 100).eggDrop());
        System.out.println(new EggDropVersion3(100, 50).eggDrop());
        System.out.println(new EggDropVersion3(100, 10).eggDrop());
    }
}
