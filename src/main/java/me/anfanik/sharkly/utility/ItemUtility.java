package me.anfanik.sharkly.utility;

import lombok.val;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.bukkit.Material.AIR;

public class ItemUtility {

    public static String toBase64(ItemStack item) throws IOException {
        if (item == null) {
            item = new ItemStack(AIR);
        }

        try (val outputStream = new ByteArrayOutputStream();
             val dataOutput = new BukkitObjectOutputStream(outputStream)) {
            dataOutput.writeObject(item);
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
    }

    public static ItemStack fromBase64(String data) throws IOException {
        if (data.isEmpty()) {
            return new ItemStack(AIR);
        }

        try (val inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
             val dataInput = new BukkitObjectInputStream(inputStream)) {
            val item = dataInput.readObject();
            dataInput.close();
            return (ItemStack) item;
        } catch (ClassNotFoundException exception) {
            throw new IOException("unable to decode class type", exception);
        } catch (ClassCastException exception) {
            throw new IOException("deserialized object is not of type ItemStack", exception);
        }
    }

}
