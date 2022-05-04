package helper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

public class FileHandler {

    public static void write(String file, String input) {
        try {
            Files.write(Path.of(file), input.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String file) throws Exception {
        if(file == null || file.length() < 2 || !file.contains(".")) throw new Exception("Keinen validen Dateinamen angegeben.");
        String content = "";
        try {
            Path p = Path.of(file);
            if(Files.exists(p)) {
                content = new String(Files.readAllBytes(p));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static <T> T readJson(String file, Class<T> cls) throws Exception {
        String output = FileHandler.read(file);
        Gson gson = new Gson();
        return gson.fromJson(output, (Type) cls);
    }

    public static <T> void writeJson(String file, T cls) {
        Gson gson = new Gson();
        FileHandler.write(file, gson.toJson(cls));
    }
}

