package cn.nukkit.network.protocol;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.ToString;

import java.util.List;

/**
 * Created by CreeperFace on 5.3.2017.
 */
@ToString
public class MapInfoRequestPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.MAP_INFO_REQUEST_PACKET;

    public long mapId;

    /**
     * Sends pixels that are generated by the client to the server.
     *
     * @since 1.19.20
     */
    private final List<MapPixel> pixels = new ObjectArrayList<>();

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        mapId = this.getVarLong();

        if (this.protocol >= ProtocolInfo.v1_19_20) {
            int count = (int) this.getUnsignedVarInt();
            for (int i = 0; i < count; i++) {
                MapPixel pixel = new MapPixel();
                pixel.pixel = this.getInt();
                pixel.index = this.getLShort();
                this.pixels.add(pixel);
            }
        }
    }

    @Override
    public void encode() {
    }

    public static class MapPixel {
        /**
         * Colour value of pixel
         */
        int pixel;
        /**
         * Pixel index in map.
         */
        int index;
    }
}
