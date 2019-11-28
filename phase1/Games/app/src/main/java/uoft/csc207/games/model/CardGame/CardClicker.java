package uoft.csc207.games.model.CardGame;

public interface CardClicker {

    void clickSummon(CardGameState cardGameState, int posIndex);

    void clickAttack(CardGameState cardGameState, int posIndex);
}
