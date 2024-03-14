package com.ljj.easesim.commands;

import com.ljj.easesim.elements.Light;
import com.ljj.easesim.interfaces.Command;

public class ToggleIsAutoLightCommand implements Command {
    private Light light;

    public ToggleIsAutoLightCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.setIsAutoState(!light.getIsAutoState());
    }
}
