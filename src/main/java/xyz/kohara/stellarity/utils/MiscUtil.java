package xyz.kohara.stellarity.utils;

public class MiscUtil {
    public static <T extends Throwable> T initThrowableCause(T original, Throwable cause) {
        original.initCause(cause);
        return original;
    }
}