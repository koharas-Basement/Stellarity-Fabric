package xyz.kohara.stellarity.utils;

public class MiscUtil {
    //When the throwable constructor doesnt support this
    public static <T extends Throwable> T initThrowableCause(T original, Throwable cause) {
        original.initCause(cause);
        return original;
    }
    
    /**
     * use this if you feel very devious<p>
     * also the built-in linter is stupid and you should place the {@code return} statement right after throwing
     * @param t throwable
     * @param <T> throwable
     * @throws T throwable
     */
    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
}