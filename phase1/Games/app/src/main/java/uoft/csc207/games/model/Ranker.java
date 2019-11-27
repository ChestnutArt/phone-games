package uoft.csc207.games.model;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collection;

import uoft.csc207.games.controller.ProfileManager;

public class Ranker {
    private ArrayList profiles;
    public Ranker(ArrayList profiles){
        this.profiles = profiles;
    }

    public ArrayList createListByScore(int l, int r){
        if (l >= r){
            return profiles;
        }
        else{
            PlayerProfile pivot = (PlayerProfile)profiles.get(r);
            int cnt = l;
            for (int i = l; i <= profiles.size(); i++)
            {
                PlayerProfile p = (PlayerProfile)profiles.get(i);
                if (p.getScore() <= pivot.getScore())
                {
                    swap(profiles.get(cnt), profiles.get(i));
                    cnt++;
                }
            }
            createListByScore(l, cnt - 2);
            createListByScore(cnt, r);
            return profiles;
        }
    }
    public ArrayList createListByCurrency(int l, int r){
        if (l >= r){
            return profiles;
        }
        else{
            PlayerProfile pivot = (PlayerProfile)profiles.get(r);
            int cnt = l;
            for (int i = l; i <= profiles.size(); i++)
            {
                PlayerProfile p = (PlayerProfile)profiles.get(i);
                if (p.getCurrency() <= pivot.getCurrency())
                {
                    swap(profiles.get(cnt), profiles.get(i));
                    cnt++;
                }
            }
            createListByCurrency(l, cnt - 2);
            createListByCurrency(cnt, r);
            return profiles;
        }
    }

    public ArrayList createListByAchievements(int l, int r){
        if (l >= r){
            return profiles;
        }
        else{
            PlayerProfile pivot = (PlayerProfile)profiles.get(r);
            int cnt = l;
            for (int i = l; i <= profiles.size(); i++)
            {
                PlayerProfile p = (PlayerProfile)profiles.get(i);
                if (p.getNumAchievements() <= pivot.getNumAchievements())
                {
                    swap(profiles.get(cnt), profiles.get(i));
                    cnt++;
                }
            }
            createListByAchievements(l, cnt - 2);
            createListByAchievements(cnt, r);
            return profiles;
        }
    }

    private void swap(Object a, Object b)
    {
        Object temp = a;
        a = b;
        b = temp;
    }

}
