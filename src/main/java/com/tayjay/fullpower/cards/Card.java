package com.tayjay.fullpower.cards;

/**
 * The Card object class.
 *
 * Created by tayjay
 */
public class Card
{
    private String cardName;
    private int rarity;
    private int setId;
    private int id;
    private String[] setNames = {"Blocks"};
    private String setName;

    /**
     * Create a new Card.
     * @param id
     * @param name
     * @param rarity
     * @param set
     */
    public Card(int id, String name, int rarity, int set)
    {
        this.id = id;
        this.cardName = name;
        this.rarity = rarity;
        this.setName = setNames[set];
    }

    public String getCardName()
    {
        return cardName;
    }

    public int getRarity()
    {
        return rarity;
    }

    public String getSetName()
    {
        return setName;
    }

    public int getId()
    {
        return id;
    }

}
