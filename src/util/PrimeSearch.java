package util;

import java.util.Random;

class PrimeSearch {
    static int prime_array[];
    int skip;
    int currentPosition;
    int maxElements;
    int currentPrime;
    int searches;

    public PrimeSearch(int elements) {

    }

    public int GetNext(boolean restart) {
        return 0;
    }

    public boolean Done() {
        return (searches == prime_array[currentPrime]);
    }

    public void Restart() {
        currentPosition = 0;
        searches = 0;
    }

    public static void main(String[] args) {
        int prime = 11;
        int ra = new Random().nextInt(prime) + 1;
        int rb = new Random().nextInt(prime) + 1;
        int rc = new Random().nextInt(prime) + 1;
        System.out.println(ra + " " + rb + " " + rc);
    }
};