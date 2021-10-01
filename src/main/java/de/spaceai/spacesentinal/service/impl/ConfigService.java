package de.spaceai.spacesentinal.service.impl;

import de.spaceai.spacesentinal.service.Service;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigService extends Service {

    private final File file;

    public ConfigService(String serviceName) {
        super(serviceName);
        this.file = new File("plugins/SpaceSentinal/config.yml");
    }

    public void createIfNotExist() {
        if(file.exists())
            return;
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        fileConfiguration.set("error.no-economy", "&4&oEs wurde kein registrierter EconomyService gefunden.");
        fileConfiguration.set("message.economy", "&7&oDein Geld: &e%0%");
        fileConfiguration.set("message.money-set", "&aNeuer Kontostand von %0%: %1%");
        fileConfiguration.set("message.no-permission", "&cDazu hast du keine Rechte");
        fileConfiguration.set("error.invalid-amount", "&4Bitte gib einen gültigen Betrag an");
        fileConfiguration.set("error.user-not-found", "&4Dieser Spieler konnte nicht gefunden werden.");

        try {
            fileConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String path, Object o) {
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        fileConfiguration.set(path, o.toString());
        try {
            fileConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String path, Object... replacements) {
        int i = 0;
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        String str = fileConfiguration.getString(path);
        str = str.replaceAll("&", "§");
        for(Object o : replacements)
            str = str.replaceAll("%"+(i++)+"%", o.toString());
        return str;
    }

}
