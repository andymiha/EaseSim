package com.ljj.easesim.commands;

import com.ljj.easesim.elements.Light;
import com.ljj.easesim.interfaces.Command;

public class ToggleLightCommand implements Command {
    private Light light;

    public ToggleLightCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.toggleLight();
    }
}
