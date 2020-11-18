package me.uselessmnemonic.whistles.persistence;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.nio.ByteBuffer;
import java.util.UUID;

public class PersistentDataLocation implements PersistentDataType<byte[], Location> {

    public static final PersistentDataLocation instance = new PersistentDataLocation();

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<Location> getComplexType() {
        return Location.class;
    }

    @Override
    public byte[] toPrimitive(Location complex, PersistentDataAdapterContext context) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[48]);
        UUID worldUUID = complex.getWorld().getUID();
        bb.putLong(worldUUID.getMostSignificantBits());
        bb.putLong(worldUUID.getLeastSignificantBits());
        bb.putDouble(complex.getX());
        bb.putDouble(complex.getY());
        bb.putDouble(complex.getZ());
        bb.putFloat(complex.getYaw());
        bb.putFloat(complex.getPitch());
        return bb.array();
    }

    @Override
    public Location fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        ByteBuffer bb = ByteBuffer.wrap(primitive);
        World world = Bukkit.getWorld(new UUID(bb.getLong(), bb.getLong()));
        double x = bb.getDouble(), y = bb.getDouble(), z = bb.getDouble();
        float yaw = bb.getFloat(), pitch = bb.getFloat();
        return new Location(world, x, y, z, yaw, pitch);
    }

}
