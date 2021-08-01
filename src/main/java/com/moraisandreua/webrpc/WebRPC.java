package com.moraisandreua.webrpc;

import org.bukkit.plugin.java.JavaPlugin;

public final class WebRPC extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("WebRPC started!!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("WebRPC stopped!!");
    }
}
