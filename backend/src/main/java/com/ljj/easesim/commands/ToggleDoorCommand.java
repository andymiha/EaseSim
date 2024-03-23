package com.ljj.easesim.commands;

import com.ljj.easesim.elements.Door;
import com.ljj.easesim.interfaces.Command;

public class ToggleDoorCommand implements Command {
    private Door door;

    public ToggleDoorCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.toggle();
    }
}
