package me.uselessmnemonic.whistles.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.UUID;

public class PersistentDataUUID implements PersistentDataType<byte[], UUID> {

    public static final PersistentDataUUID instance = new PersistentDataUUID();

    @Override
    @Nonnull
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    @Nonnull
    public Class<UUID> getComplexType() {
        return UUID.class;
    }

    @Override
    @Nonnull
    public byte[] toPrimitive(@Nonnull UUID complex, @Nonnull PersistentDataAdapterContext context) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(complex.getMostSignificantBits());
        bb.putLong(complex.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    @Nonnull
    public UUID fromPrimitive(@Nonnull byte[] primitive, @Nonnull PersistentDataAdapterContext context) {
        ByteBuffer bb = ByteBuffer.wrap(primitive);
        long firstLong = bb.getLong();
        long secondLong = bb.getLong();
        return new UUID(firstLong, secondLong);
    }

}
