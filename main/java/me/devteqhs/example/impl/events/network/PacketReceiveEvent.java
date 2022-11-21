package me.devteqhs.example.impl.events.network;

import me.devteqhs.example.api.event.Event;
import net.minecraft.network.Packet;

public class PacketReceiveEvent extends Event {

    private Packet<?> packet;

    public PacketReceiveEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public void setPacket(Packet<?> p) {
        packet = p;
    }

}
