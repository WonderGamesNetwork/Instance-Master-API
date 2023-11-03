package fr.mrcubee.im;

import net.md_5.bungee.api.plugin.Plugin;

import java.lang.reflect.Field;

public interface InstanceMasterAPI {

    private static Plugin getClassPlugin(final Class<?> clazz) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, ClassCastException {
        final Class<?> classPluginClassLoader = Class.forName("net.md_5.bungee.api.plugin.PluginClassloader");
        final ClassLoader classLoader = clazz.getClassLoader();
        final Field pluginField;

        if (!classPluginClassLoader.isInstance(classLoader))
            return null;
        pluginField = classLoader.getClass().getField("plugin");
        pluginField.setAccessible(true);
        return (Plugin) pluginField.get(classLoader);
    }

    public static InstanceMasterAPI getInstance() {
        Plugin plugin = null;

        try {
            plugin = getClassPlugin(InstanceMasterAPI.class);
        } catch (Exception ignored) {}
        return (plugin instanceof InstanceMasterAPI) ? (InstanceMasterAPI) plugin : null;
    }

}
