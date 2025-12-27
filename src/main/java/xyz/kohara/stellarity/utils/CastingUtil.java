package xyz.kohara.stellarity.utils;

import net.minecraft.world.damagesource.DamageSources;
import xyz.kohara.stellarity.interface_injection.ExtDamageSources;

/**
 * WARNING<p>
 * THIS ONE IS CAN BE EXTREMELY VERSION DEPENDENT<p>
 * also use in conjunction with {@code var},<p>
 * i.e. {@code var item = CastingUtil.item(...);}
 */
@SuppressWarnings({"unchecked"})//if anything throws then something is extremely wrong here
public class CastingUtil {
    public static
    <T extends DamageSources & ExtDamageSources>
    T damageSources(DamageSources sources) {return cast(sources);}
    
    private static <T> T cast(Object o) {
        try {
            return (T) o;
        } catch (ClassCastException e) {
            throw MiscUtil.initThrowableCause(new UnknownError("WHAT THE FUCK IS THIS?!?!?!?!"), e);
        }
    }
}