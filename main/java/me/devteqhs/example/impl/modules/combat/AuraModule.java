package me.devteqhs.example.impl.modules.combat;

import me.devteqhs.example.api.event.EventState;
import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.EventLink;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.api.property.sub.BooleanProperty;
import me.devteqhs.example.api.property.sub.DoubleProperty;
import me.devteqhs.example.api.property.sub.EnumProperty;
import me.devteqhs.example.api.property.sub.MultiSelectEnumProperty;
import me.devteqhs.example.impl.events.player.MotionEvent;
import me.devteqhs.example.impl.utils.misc.TimerUtils;
import me.devteqhs.example.impl.utils.random.RandomUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@ModuleInfo(name = "Aura", category = ModuleCategory.COMBAT)
public class AuraModule extends Module {

    public DoubleProperty attackRange = new DoubleProperty("Attack Range", 4.2, 3, 6, 0.1);
    public DoubleProperty maxCPS = new DoubleProperty("Max CPS", 10, 0, 20, 1);
    public DoubleProperty minCPS = new DoubleProperty("Min CPS", 5, 0, 20, 1);
    public BooleanProperty throughWalls = new BooleanProperty("Through Walls", false);
    public MultiSelectEnumProperty<Targets> targets = new MultiSelectEnumProperty<>("Targets", new Targets[]{Targets.PLAYERS});

    public AuraModule() {
        addProperties(attackRange, maxCPS, minCPS, throughWalls, targets);
    }

    public final List<EntityLivingBase> targetList = new ArrayList<>();
    public Entity target;
    public boolean attacking;
    public TimerUtils timer = new TimerUtils();

    @EventLink
    private final Listener<MotionEvent> motionEventListener = event -> {
        setSuffix("R:" + attackRange.getValue().toString());
        sortTargets();
        if(event.getEventState() != EventState.PRE) {
            target = targetList.get(0);
            if(timer.hasTimeElapsed(RandomUtils.getRandomBetween(minCPS.getValue().intValue(), maxCPS.getValue().intValue()))) {
                mc.thePlayer.swingItem();
                mc.getNetHandler().addToSendQueue(new C02PacketUseEntity(target, C02PacketUseEntity.Action.ATTACK));
                timer.reset();
            } else {
                target = null;
                timer.reset();
            }
        }
    };

    @Override
    public void onDisable() {
        target = null;
        targetList.clear();
        attacking = false;
        super.onDisable();
    }

    private void sortTargets() {
        targetList.clear();
        for (Entity entity : mc.theWorld.getLoadedEntityList()) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
                if (mc.thePlayer.getDistanceToEntity(entity) <= attackRange.getValue() && isValid(entity) && mc.thePlayer != entityLivingBase) {
                    targetList.add(entityLivingBase);
                }
            }
        }
        targetList.sort(Comparator.comparingDouble(mc.thePlayer::getDistanceToEntity));
    }

    public boolean isValid(Entity entity) {
        if (entity == null) return false;

        if (entity instanceof EntityPlayer) {
            boolean canSeeEntity = mc.thePlayer.canEntityBeSeen(entity);
            boolean isInvisible = entity.isInvisible();

            if (targets.isSelected(Targets.PLAYERS) && !isInvisible && canSeeEntity)
                return true;
            if (targets.isSelected(Targets.INVISIBLES) && isInvisible)
                return true;
            if (throughWalls.getValue() && !canSeeEntity)
                return true;
        }

        if (entity instanceof EntityAnimal && targets.isSelected(Targets.ANIMALS))
            return true;

        if (entity instanceof EntityMob && targets.isSelected(Targets.MOBS))
            return true;

        if (entity.isInvisible() && targets.isSelected(Targets.INVISIBLES))
            return true;

        return false;
    }

    public enum Targets {
        PLAYERS, MOBS, ANIMALS, INVISIBLES, DEAD;
    }
}

