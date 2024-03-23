package com.ljj.easesim.commands;

import com.ljj.easesim.elements.Window;
import com.ljj.easesim.abstractions.Command;

public class ToggleWindowCommand implements Command {
    private Window window;

    public ToggleWindowCommand(Window window) {
        this.window = window;
    }

    @Override
    public void execute() {
        window.toggle();
    }
}
