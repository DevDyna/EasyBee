package com.devdyna.easybee.Utils;

public class Calc {

    public Calc() {

    }

    public static int rndSelector(int size) {
        return rnd(0, size-1);
    }

    public static double rnd(double min, double max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    public static int rnd(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

    public static boolean rnd50() {
        return Math.random() < 0.5;
    }

    public static boolean rnd75() {
        return Math.random() < 0.75;
    }

    public static boolean rnd25() {
        return Math.random() < 0.25;
    }

    public static boolean rnd99() {
        return Math.random() < 0.99;
    }

    public static boolean rnd90() {
        return Math.random() < 0.90;
    }

    public static boolean rnd05() {
        return Math.random() < 0.05;
    }

    public static boolean rnd10() {
        return Math.random() < 0.1;
    }

    public static boolean rndCustom(int percentuage) {
        return Math.random() < (percentuage / 100);
    }

}
