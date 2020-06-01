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

public class EnemyFactory implements AbstractEnemyFactory {

    Gson gson = new Gson();

    @Override
    public Character createEnemy() {
        List<Character> list = null;
        try {
            String path = new File("").getAbsolutePath().concat("/src/sample/assets/enemies/Enemies.json");
            Reader reader = new FileReader(path);
            list = gson.fromJson(reader, new TypeToken<List<Character>>() {}.getType());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        Random rand = new Random();
        Character enemy = list.get(ThreadLocalRandom.current().nextInt(0, list.size() - 1));
        enemy.setTurnState(TurnState.WAITING);
        return enemy;
    }
}
