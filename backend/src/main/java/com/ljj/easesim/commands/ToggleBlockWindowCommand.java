package com.ljj.easesim.commands;

import com.ljj.easesim.elements.Window;
import com.ljj.easesim.interfaces.Command;

public class ToggleBlockWindowCommand implements Command {
    private Window window;
    private boolean isBlocked;

    public ToggleBlockWindowCommand(Window window) {
        this.window = window;
        this.isBlocked = false;
    }

    @Override
    public void execute() {
        isBlocked = !isBlocked;
        window.setToggleable(!isBlocked);
    }
}
