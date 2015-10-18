package com.tayjay.fullpower.cards;

import com.tayjay.fullpower.reference.Names;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tayjm_000 on 2015-10-17.
 */
public class CardRegistry
{
    public static List<Card> cardsList = new ArrayList<Card>();
    public static Card stoneCard;
    public static Card grassCard;
    public static int totalCards;

    public static void init()
    {
        registerCards();
        fillList();
    }

    public static void registerCards()
    {
        int id = -1;
        stoneCard = new Card(++id, Names.Cards.STONE_BLOCK,0,0);
        grassCard = new Card(++id,Names.Cards.GRASS_BLOCK,0,0);
        totalCards = id;
    }

    public static void fillList()
    {
        cardsList.add(stoneCard.getId(),stoneCard);
        cardsList.add(grassCard.getId(),grassCard);
    }

    public static Card getCardById(int id)
    {
        return cardsList.get(id);
    }

}
