package me.devteqhs.example.impl.modules.render;

import me.devteqhs.example.Example;
import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.module.Category;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.impl.events.render.Render2DEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.util.*;

@ModuleInfo(name = "HUD", key = Keyboard.KEY_L, category = Category.RENDER)
public class HUDMod extends Module {

    @Target
    private final Listener<Render2DEvent> render2DEventListener = event -> {
      mc.fontRendererObj.drawStringWithShadow("Example", 2, 2, -1);
        List<Module> modules = new ArrayList<>();
        int posY = 2;
        ScaledResolution sr = new ScaledResolution(mc);
        for (Module module : Example.INSTANCE.getModuleManager().getModules()) {
            if(module.isToggled()) {
                modules.add(module);
                modules.sort(Comparator.comparingDouble(module1 -> mc.fontRendererObj.getStringWidth(((Module) module1).getDisplayName())).reversed());
            }
        }
        for (Module module : modules) {
            mc.fontRendererObj.drawStringWithShadow(module.getDisplayName(), sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(module.getDisplayName()) - 2, posY, -1);
            posY += 12;
        }
        String fps = String.valueOf(Minecraft.getDebugFPS());
        mc.fontRendererObj.drawStringWithShadow("FPS:§7 " + fps, 2, sr.getScaledHeight() - mc.fontRendererObj.FONT_HEIGHT - 1, -1);
    };

}
