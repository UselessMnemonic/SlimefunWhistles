package me.uselessmnemonic.whistles.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;

public class PersistentDataBoolean implements PersistentDataType<Byte, Boolean> {

    public static final PersistentDataBoolean instance = new PersistentDataBoolean();

    @Override
    @Nonnull
    public Class<Byte> getPrimitiveType() {
        return Byte.class;
    }

    @Override
    @Nonnull
    public Class<Boolean> getComplexType() {
        return Boolean.class;
    }

    @Override
    @Nonnull
    public Byte toPrimitive(@Nonnull Boolean complex, @Nonnull PersistentDataAdapterContext persistentDataAdapterContext) {
        return complex ? (byte)1 : 0;
    }

    @Override
    @Nonnull
    public Boolean fromPrimitive(@Nonnull Byte primitive, @Nonnull PersistentDataAdapterContext persistentDataAdapterContext) {
        return primitive == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

}
