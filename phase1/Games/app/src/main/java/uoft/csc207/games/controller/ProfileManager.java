package uoft.csc207.games.controller;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

import uoft.csc207.games.model.PlayerProfile;

public class ProfileManager {
    /*
        IMPORTANT: PLEASE DON'T CHANGE ANYTHING IN THIS CLASS. IF YOU THINK THERE'S AN ISSUE, CONTACT
        WILLIAM IN THE GROUP CHAT
     */

    private ProfileManager(){}
    public static String CURRENT_PLAYER = "currentPlayer";
    private static String NAME_OF_PROFILE_STORE = "players.profiles";

    private static TreeMap<String, PlayerProfile> profileMap;

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

    private Context appContext;

    public PlayerProfile getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerProfile currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private PlayerProfile currentPlayer;

    private static ProfileManager singletonProfileManager;
    public static ProfileManager getProfileManager(Context appContext){
        if(singletonProfileManager == null){
            singletonProfileManager = new ProfileManager();
            singletonProfileManager.init(appContext);
            return singletonProfileManager;
        }else {
            return singletonProfileManager;
        }
    }

    private void init(Context appContext){
        this.appContext = appContext;
        loadProfiles();
    }
    public PlayerProfile getProfileById(String id){
        return profileMap.get(id);
    }


    public void createProfile(PlayerProfile profile){
        boolean profileExist = false;
        if(profileMap.get(profile.getId()) != null){
            profileMap.replace(profile.getId(), profile);
        }else {
            profileMap.put(profile.getId(), profile);
        }

    }
    private void loadProfiles(){

        File dir2store = getAppContext().getFilesDir();
        String filePath = dir2store.getPath() + File.pathSeparator + NAME_OF_PROFILE_STORE;
        FileInputStream fileInputStream = null;
        try {
            File fileStoredProfiles = new File(filePath);
            if(!fileStoredProfiles.exists()){
                profileMap = new TreeMap<String, PlayerProfile>();
                return;
            }
            fileInputStream = new FileInputStream(new File(filePath));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            profileMap = (TreeMap<String, PlayerProfile>)objectInputStream.readObject();
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

    public void saveProfiles(){
        File dir2store = getAppContext().getFilesDir();
        String filePath = dir2store.getPath() + File.pathSeparator + NAME_OF_PROFILE_STORE;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(filePath));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(profileMap);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
