package me.devteqhs.example.impl.events.network;

import me.devteqhs.example.api.event.Event;
import net.minecraft.network.Packet;

public class PacketSendEvent extends Event {

    private Packet<?> packet;

    public PacketSendEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public void setPacket(Packet<?> p) {
        packet = p;
    }

}
