package me.uselessmnemonic.whistles.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class PersistentDataBoolean implements PersistentDataType<Byte, Boolean> {

    public static final PersistentDataBoolean instance = new PersistentDataBoolean();

    @Override
    public Class<Byte> getPrimitiveType() {
        return Byte.class;
    }

    @Override
    public Class<Boolean> getComplexType() {
        return Boolean.class;
    }

    @Override
    public Byte toPrimitive(Boolean complex, PersistentDataAdapterContext persistentDataAdapterContext) {
        return complex ? (byte)1 : 0;
    }

    @Override
    public Boolean fromPrimitive(Byte primitve, PersistentDataAdapterContext persistentDataAdapterContext) {
        return primitve == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

}
