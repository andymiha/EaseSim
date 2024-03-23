package com.ljj.easesim.commands;

import com.ljj.easesim.elements.Door;
import com.ljj.easesim.abstractions.Command;

public class ToggleIsAutoDoorCommand implements Command {
    private Door door;

    public ToggleIsAutoDoorCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.setIsAutoState(!door.getIsAutoState());
    }
}
