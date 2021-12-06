package org.example.plugin;

public interface PluginFactory<T extends Plugin> {
   String getName();
   T create();
}
