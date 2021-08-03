package com.moraisandreua.webrpc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import spark.Spark;

public final class WebRPC extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        // Plugin startup logic
        System.out.println("[ WebRPC ] started!!");

        getConfig().options().copyDefaults(); // load the file
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);


        Spark.get("/", (request, response) -> {

            String accessToken = getConfig().getString("accessToken");
            System.out.println("[ WebRPC ] website command received!!");
            if(!request.queryParams("accessToken").equals(accessToken)) {
                System.out.println(request.queryParams("accessToken"));
                System.out.println(accessToken);
                return false;
            }

            String command = request.queryParams("command");

            sendCommand(command);
            return command;
        });
        Spark.init();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[ WebRPC ] stopped!!");
    }

    public void sendCommand(String c){
        Bukkit.getScheduler().runTask(this, ()->Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(command.getName().equals("webrpc")){
            Player player = (Player) sender;

            if(player.hasPermission("webrpc.accessToken")){
                String accessToken = getConfig().getString("accessToken");
                player.sendMessage(ChatColor.DARK_AQUA + "Your website's access token is " + ChatColor.GREEN + accessToken);
                player.sendMessage(ChatColor.DARK_AQUA + "You can get it at configuration file");
            }else{
                player.sendMessage(ChatColor.DARK_RED + "You don't have permissions to know that (webrpc.accessToken)");
            }
        }

        return false;
    }

}
