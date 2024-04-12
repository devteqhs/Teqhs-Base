package me.devteqhs.example.impl.modules.render;

import me.devteqhs.example.Example;
import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.impl.events.render.Render2DEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.util.*;

@ModuleInfo(name = "HUD", key = Keyboard.KEY_L, category = ModuleCategory.RENDER)
public class HUDMod extends Module {

    @Target
    private final Listener<Render2DEvent> render2DEventListener = event -> {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fontRenderer = mc.fontRendererObj;
        ScaledResolution sr = new ScaledResolution(mc);

        List<Module> modules = new ArrayList<>();
        for (Module module : Example.getInstance().getModuleManager().getModules()) {
            if (module.isToggled()) {
                modules.add(module);
            }
        }
        modules.sort(Comparator.comparingDouble(module1 -> mc.fontRendererObj.getStringWidth(((Module) module1).getDisplayName())).reversed());

        int posY = 2;
        int screenWidth = sr.getScaledWidth();
        for (Module module : modules) {
            String displayName = module.getDisplayName();
            int stringWidth = fontRenderer.getStringWidth(displayName);
            fontRenderer.drawStringWithShadow(displayName, screenWidth - stringWidth - 2, posY, 0xFFFFFFFF);
            posY += 12;
        }

        String fps = String.valueOf(Minecraft.getDebugFPS());
        int screenHeight = sr.getScaledHeight();
        fontRenderer.drawStringWithShadow("FPS:§7 " + fps, 2, screenHeight - fontRenderer.FONT_HEIGHT - 1, 0xFFFFFFFF);
    };

}
