package com.playernguyen.opteco.configuration;

import com.playernguyen.opteco.OptEco;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

public abstract class Yamlist<T extends Flagable> {

    private final String parent;
    private final String name;
    private File file;
    private FileConfiguration fileConfiguration;
    private final OptEco optEco;

    public Yamlist(OptEco optEco, String name, T[] flagables, boolean save, String parent) {
        this.optEco = optEco;
        this.name = name;
        this.parent = parent;
        // Load parent
        File _parent = new File(optEco.getDataFolder(), parent);
        if (!_parent.exists() && !_parent.mkdir())
            throw new NullPointerException("Parent directory not found");
        // Initial the vars
        this.file = new File(_parent, name);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        // Load flagable
        for (T flagable : flagables) {
            if (!this.fileConfiguration.contains(flagable.getPath())) {
                this.fileConfiguration.addDefault(flagable.getPath(), flagable.getDefine());
            }
        }

        // Save
        if (save) save();
    }

    public Yamlist(OptEco optEco, String name, String parent) throws IOException {
        this.optEco = optEco;
        this.name = name;
        this.parent = parent;
        // Load parent
        load(name, parent);

    }

    private void load(String localFile, String parent) throws IOException {
        File _parent = new File(optEco.getDataFolder(), parent);
        if (!_parent.exists() && !_parent.mkdir())
            throw new NullPointerException("Parent directory not found");
        // Load and children
        InputStream resource = optEco.getResource(localFile);
        if (resource == null) {
            throw new NullPointerException(String.format("Cannot found file %s in /resources", localFile));
        }
        // Initial file and put it into
        this.file = new File(_parent, localFile);
        if (!file.exists()) {
            if (file.createNewFile()) {
                this.writeFileFromInputStream(optEco, resource);
            }
        }
        // Now load this configuration
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    private File getFile() {
        return file;
    }

    private FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    private void save () {
        try {
            this.fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFileFromInputStream(OptEco optEco, InputStream resource) throws IOException {
        Scanner scanner = new Scanner(resource);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write a song :D
            while (scanner.hasNextLine()) {
                String next = scanner.nextLine().replace("%version%", optEco.getDescription().getVersion());
                writer.write(next + System.lineSeparator());
            }
            writer.flush();
        }
    }

    /**
     *
     * @throws IOException Cannot save the file
     */
    private void reimport(String localName, String parent) throws IOException {
        // Save old setting
        File _file = new File(optEco.getDataFolder(), "temp_save.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(_file);
        Set<String> keys = this.getFileConfiguration().getKeys(true);
        keys.forEach(e-> configuration.set(e, getFileConfiguration().get(e)));
        configuration.save(_file);

        // Remove old setting
        if (!this.file.delete()) {
            throw new IllegalStateException("Cannot delete config file.");
        }

        // Create new one
        load(localName, parent);

        // Set the stores
        Set<String> tempKeys = configuration.getKeys(true);
        tempKeys.forEach(e -> this.fileConfiguration.set(e, configuration.get(e)));
        save();

        // Remove the store
        if (!_file.delete()) {
            throw new IllegalStateException("Cannot delete temporarily file.");
        }

    }

    public Object get(T flag) {
        if (!getFileConfiguration().contains(flag.getPath())) {
            try {
                reimport(name, parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getFileConfiguration()
                .get(flag.getPath());
    }

    public String getString(T flag) {
        return (String) get(flag);
    }

    public boolean getBoolean(T flag){
        return (boolean) get(flag);
    }
}
