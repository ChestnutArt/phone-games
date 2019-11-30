package uoft.csc207.games.model;


import java.util.ArrayList;
import java.util.List;

import uoft.csc207.games.controller.Score;

public class Ranker {
    public ArrayList<Score> scores;
    public Ranker(ArrayList scores){
        this.scores = scores;
    }

    public ArrayList<Score> createListByScore(int l, int r){
        if (l >= r){
            return scores;
        }
        else{
            Score pivot = scores.get(r);
            int cnt = l;
            for (int i = l; i < scores.size(); i++)
            {
                Score p = scores.get(i);
                if (p.getPoints() <= pivot.getPoints())
                {
                    swap(cnt, i, scores);
                    cnt++;
                }
            }
            createListByScore(l, cnt - 2);
            createListByScore(cnt, r);
            return scores;
        }
    }
    public ArrayList<Score> createListByCurrency(int l, int r){
        if (l >= r){
            return scores;
        }
        else{
            Score pivot = scores.get(r);
            int cnt = l;
            for (int i = l; i < scores.size(); i++)
            {
                Score p = scores.get(i);
                if (p.getMoney() <= pivot.getMoney())
                {
                    swap(cnt, i, scores);
                    cnt++;
                }
            }
            createListByCurrency(l, cnt - 2);
            createListByCurrency(cnt, r);
            return scores;
        }
    }

    private void swap(int a, int b, ArrayList s)
    {
        Object temp = s.get(a);
        if (s.size() > Math.max(a,b)) {
            s.set(a, s.get(b));
            s.set(b, temp);
        }
    }

    public void addScore(Score p){
       scores.add(p);
    }

    public int getSize(){
        return scores.size();
    }

}
