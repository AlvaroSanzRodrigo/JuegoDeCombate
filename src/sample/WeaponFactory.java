package sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WeaponFactory implements AbstractWeaponFactory {

    Gson gson = new Gson();

    @Override
    public Weapon createWeapon() {
        List<Weapon> list = null;
        try {
            String path = new File("").getAbsolutePath().concat("/src/sample/assets/weapons/Weapons.json");
            Reader reader = new FileReader(path);
            list = gson.fromJson(reader, new TypeToken<List<Weapon>>() {}.getType());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        Random rand = new Random();
        assert list != null;
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size() - 1));
    }
}
