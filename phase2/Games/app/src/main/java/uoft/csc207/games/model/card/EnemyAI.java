package uoft.csc207.games.model.card;

import android.widget.ImageView;

public class EnemyAI implements CardClicker {

    private CardDeck aiDeck;
    private ImageView[] aiHandView;
    private CardCollection aiBoard, aiHand;

    public EnemyAI() {
        aiDeck = new CardDeck();
    }

    @Override
    public void clickSummon(CardGameState cardGameState, int posIndex) {
        if (!cardGameState.getAiBoardOccupied(posIndex)) {
            if (cardGameState.getAiHandOccupied(posIndex)) {
                cardGameState.setAiBoard(posIndex, cardGameState.getAiHand(0));
                cardGameState.setAiHand(posIndex, CardCollection.emptyCard);
            }
        }
    }

    @Override
    public void clickAttack(CardGameState cardGameState, int posIndex, int targetPosIndex) {
        if (cardGameState.getAiBoardOccupied(posIndex)) {
            if (cardGameState.getPlayerBoardOccupied(targetPosIndex)) {
                int damageDifference =
                        ((MonsterCard) cardGameState.getAiBoard(posIndex)).getAttack() -
                                ((MonsterCard) cardGameState.getPlayerBoard(targetPosIndex)).getAttack();
                if (damageDifference > 0) {
                    cardGameState.setPlayerBoard(targetPosIndex, CardCollection.emptyCard);
                    cardGameState.attack(damageDifference, "player");
                } else if (damageDifference < 0) {
                    cardGameState.setAiBoard(posIndex, CardCollection.emptyCard);
                    cardGameState.attack(damageDifference, "ai");
                } else {
                    cardGameState.setAiBoard(posIndex, CardCollection.emptyCard);
                    cardGameState.setPlayerBoard(targetPosIndex, CardCollection.emptyCard);
                }
            } else {
                cardGameState.attack(
                        ((MonsterCard) cardGameState.getAiBoard(posIndex)).getAttack(),
                        "player");
            }
        }
    }

    @Override
    public void clickDirectAttack(CardGameState cardGameState, int posIndex) {

    }

    @Override
    public void clickTargetAttack(CardGameState cardGameState, int posIndex) {

    }

    @Override
    public void clickActivate(CardGameState cardGameState, int posIndex) {

    }

    public void setUpDeck(String cardName, CardPool cardpool) {
        //Sets up the EnemyAI deck
        aiDeck.addThree(cardName, cardpool);
        aiDeck.addThree(cardName, cardpool);
        aiDeck.addThree(cardName, cardpool);
        aiDeck.addThree(cardName, cardpool);
    }

    public CardDeck getAiDeck() {
        return aiDeck;
    }
}
