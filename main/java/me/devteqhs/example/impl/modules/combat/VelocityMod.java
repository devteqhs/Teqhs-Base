package me.devteqhs.example.impl.modules.combat;

import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.module.Category;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.impl.events.network.PacketReceiveEvent;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Velocity", key = Keyboard.KEY_J, category = Category.COMBAT)
public class VelocityMod extends Module {

    @Target
    private final Listener<PacketReceiveEvent> packetReceiveEventListener = event -> {
      if(event.getPacket() instanceof S27PacketExplosion) {
          event.cancel();
      }
      if(event.getPacket() instanceof S12PacketEntityVelocity) {
          S12PacketEntityVelocity p = (S12PacketEntityVelocity) event.getPacket();
          if(p.getEntityID() == mc.thePlayer.getEntityId()) {
              event.cancel();
          }
      }
    };

}
