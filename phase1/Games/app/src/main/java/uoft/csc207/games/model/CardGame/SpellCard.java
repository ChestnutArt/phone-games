package uoft.csc207.games.model.CardGame;

public class SpellCard extends Card{

    private String spellEffect;

    public SpellCard(String name, int cardArt, String spellEffect) {
        super(name, cardArt);
        this.spellEffect = spellEffect;
    }
}
