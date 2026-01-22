package by.hobbygames.utils;

public class Waits {
    public static void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {}
    }
}
