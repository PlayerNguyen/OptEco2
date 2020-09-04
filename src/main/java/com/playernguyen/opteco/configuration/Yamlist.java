package com.playernguyen.opteco.configuration;

import com.playernguyen.opteco.OptEco;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.Scanner;

public abstract class Yamlist<T extends Flagable> {

    private final File file;
    private final FileConfiguration fileConfiguration;

    public Yamlist(OptEco optEco, String name, T[] flagables, boolean save, String parent) {
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

    public Yamlist(OptEco optEco, String localFile, String parent) throws IOException {
        // Load parent
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

    public Object get(T flag) {
        return getFileConfiguration()
                .get(flag.getPath());
    }

    public String getString(T flag) {
        return (String) get(flag);
    }

}
