package me.devteqhs.example.impl.modules.misc;

import me.devteqhs.example.api.module.ModuleCategory;
import me.devteqhs.example.api.module.Module;
import me.devteqhs.example.api.module.ModuleInfo;
import me.devteqhs.example.api.property.sub.BooleanProperty;
import me.devteqhs.example.api.property.sub.DoubleProperty;
import me.devteqhs.example.api.property.sub.EnumProperty;
import me.devteqhs.example.api.property.sub.MultiSelectEnumProperty;

/**
 * Module for testing things during development
 */

@ModuleInfo(name = "Development", category = ModuleCategory.MISC)
public class DevelopmentModule extends Module {

    public BooleanProperty booleanProperty = new BooleanProperty("Boolean", false);
    public DoubleProperty doubleProperty = new DoubleProperty("Double", 10, 0, 20, 1);
    public EnumProperty<Mode> modeEnumProperty = new EnumProperty<>("Mode", Mode.MODE_1);
    public MultiSelectEnumProperty<SelectMode> selectModeMultiSelectEnumProperty = new MultiSelectEnumProperty("Select", new SelectMode[]{SelectMode.OPTION_1, SelectMode.OPTION_2, SelectMode.OPTION_3});

    public DevelopmentModule() {
        addProperties(booleanProperty, doubleProperty, modeEnumProperty, selectModeMultiSelectEnumProperty);
    }

    public enum Mode {
        MODE_1("Mode 1"),
        MODE_2("Mode 2"),
        MODE_3("Mode 3");

        public String name;

        Mode(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

    }

    public enum SelectMode {
        OPTION_1,
        OPTION_2,
        OPTION_3
    }

}
