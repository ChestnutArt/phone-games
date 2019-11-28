package uoft.csc207.games.controller;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import uoft.csc207.games.model.PlayerProfile;
import uoft.csc207.games.model.Ranker;


public class ScoreBoard implements Serializable {
    private TreeMap<String, ArrayList<Score>> score_board;
    private Context context;
    private String NAME_OF_PROFILE_STORE = "Scoreboard";
    private Ranker ranker;

    public ScoreBoard(Context context){
        this.context = context;
        this.ranker = new Ranker(new ArrayList<Score>());
    }

    public void addScores(){
        ArrayList<ArrayList<Score>> score_list = (ArrayList<ArrayList<Score>>)score_board.values();
        for (ArrayList<Score> value: score_list){
            for (Score score: value) {
                ranker.addScore(score);
            }
        }
    }

    public void submitScore(Score submit){
        if (score_board.containsKey(submit.getName())){
            score_board.get(score_board.get(submit.getName())).add(submit);
        }
        else{
            ArrayList<Score> t = new ArrayList<>();
            t.add(submit);
            score_board.put(submit.getName(), t);
        }
    }

    public ArrayList<Score> sortScores(boolean sort_by_points, ArrayList<Score> scores){
        Ranker ranker = new Ranker(scores);
        if (sort_by_points){
            return ranker.createListByScore(0, scores.size() - 1);
        } else {
            return ranker.createListByCurrency(0, scores.size() - 1);
        }
    }

    public void loadScores(){
        File dir2store = context.getFilesDir();
        String filePath = dir2store.getPath() + File.pathSeparator + NAME_OF_PROFILE_STORE;
        FileInputStream fileInputStream = null;
        try {
            File fileStoredProfiles = new File(filePath);
            if(!fileStoredProfiles.exists()){
                score_board = new TreeMap<String, ArrayList<Score>>();
                return;
            }
            fileInputStream = new FileInputStream(new File(filePath));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            score_board = (TreeMap<String, ArrayList<Score>>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Serialize all scores to a system file
     */
    public void saveScores(){
        File dir2store = context.getFilesDir();
        String filePath = dir2store.getPath() + File.pathSeparator + NAME_OF_PROFILE_STORE;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(filePath));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(score_board);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
