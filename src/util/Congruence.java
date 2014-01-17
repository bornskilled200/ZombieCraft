package util;

import java.util.Random;

/**
 * Created by David Park on 1/16/14.
 */
public class Congruence {
    private final int N;
    Random r;
    private int x;
    private int start;
    private int a;
    private int c;
    private int M;

    public Congruence(int N) {

        r = new Random();
        this.N = N;
        M = NextLargestPowerOfTwo(N);
        c = r.nextInt(M / 2) * 2 + 1;
        a = r.nextInt(M / 4) * 4 + 1;
        start = r.nextInt(M);
        x = start;
    }

    public int next() {
        while (true) {
            x = (a * x + c) % M;
            if (x < N)
                return x;
        }
    }

    int NextLargestPowerOfTwo(int n) {
        n |= (n >> 1);
        n |= (n >> 2);
        n |= (n >> 4);
        n |= (n >> 8);
        n |= (n >> 16);
        return (n + 1);
    }

}
