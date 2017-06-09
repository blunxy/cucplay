package implementation;

import java.util.Scanner;

public class Checkout
{
    private int runningTotal = 0;
    private Scanner scanner = new Scanner(System.in);

    public void add(int count, int price) {
        runningTotal += count * price;
    }

    public int total() {
        int i = scanner.nextInt();
        System.out.println("read in: " + i);
        return runningTotal;
    }
}
