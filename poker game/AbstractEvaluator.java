package hw4;

import java.util.ArrayList;

import api.Card;
import api.Hand;
import api.IEvaluator;
import util.SubsetFinder;

/**
 * author makuforchu The class AbstractEvaluator includes common code for all
 * evaluator types.
 * 
 * TODO: Expand this comment with an explanation of how your class hierarchy is
 * organized.
 * 
 * abstract class is the parent class to AllPrimesEvaluator, CatchAllEvaluator,
 * FourOfAKindEvaluator,FullHouseEvaluator, OnePairEvaluator,StraightEvaluator,
 * StraightFlushEvaluator and ThreeOfAKindEvaluator. All of these classes extent
 * Abstract class but they overide their cansatisfy to satisfy the needs of each
 * evaluator
 * 
 */
public abstract class AbstractEvaluator implements IEvaluator {
	/*
	 * the rank of the card
	 */
	private int ranking;

	/*
	 * how many cards are in the hand
	 */
	private int handsize;

	/*
	 * the number of cards required
	 */
	private int cardsRequired;

	/*
	 * the name of the evaluator
	 */
	private String name;

	protected AbstractEvaluator(int ranking, int handSize, int cardsRequired, String name) {
		// TODO: call appropriate superclass constructor and

		this.ranking = ranking;
		this.handsize = handSize;
		this.name = name;
		this.cardsRequired = cardsRequired;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getRanking() {
		// TODO Auto-generated method stub
		return ranking;
	}

	@Override
	public int cardsRequired() {
		// TODO Auto-generated method stub
		return cardsRequired;
	}

	@Override
	public int handSize() {
		// TODO Auto-generated method stub
		return handsize;
	}

	@Override
	public boolean canSubsetSatisfy(Card[] allCards) {
		// TODO Auto-generated method stub

		if (allCards.length >= cardsRequired()) {
			ArrayList<int[]> miniset = new ArrayList<>();
			miniset = SubsetFinder.findSubsets(allCards.length, cardsRequired());
			Card[] card = new Card[cardsRequired()];
			for (int i = 0; i <= miniset.size() - 1; i++) {
				int[] s = miniset.get(i);
				for (int j = 0; j <= s.length - 1; j++) {
					card[j] = allCards[s[j]];
				}
				if (canSatisfy(card)) {
					return true;
				}
			}
		}

		return false;

	}

	@Override
	public Hand createHand(Card[] allCards, int[] subset) {
		// TODO Auto-generated method stub
		Hand hand = null;

		Card[] maincards = new Card[cardsRequired()];
		Card[] sidecards = new Card[handSize() - cardsRequired()];

		int count1 = 0;
		int count2 = 0;

		if (allCards.length >= handSize()) {
			int counter = 0;
			for (int i = 0; i < subset.length; i++) {
				maincards[i] = allCards[subset[i]];

			}
//			for (int j = 0; j < maincards.length; j++) {
//				if (j != subset[counter]) {
//					sidecards[j] = allCards[j];
//					counter++;
//				}
//			}

			for (int j = 0; j < maincards.length; j++) {
				for (int s = 0; s < sidecards.length; s++) {
					if (allCards[s] != maincards[j]) {
						sidecards[s] = allCards[s];
					}
				}
			}

			if (canSatisfy(maincards)) {
				hand = new Hand(maincards, sidecards, this);
				if (name.equals("Full House")) {
					int didi1 = maincards[0].getRank();
					int didi2 = maincards[maincards.length - 1].getRank();
//					int tempj = 0;
//					int tempw = 0;

					if (didi1 != didi2) {
						for (int k = 0; k < maincards.length; k++) {
							if (maincards[k].getRank() == didi1) {
								count1++;
							} else if (maincards[k].getRank() == didi2) {
								count2++;
							}
						}

						if (count2 > count1) {

							int num = maincards.length - count2;
							for (int s = 0; s < maincards.length; s++) {
								if (num + s < maincards.length) {
									Card temp = maincards[s];
									maincards[s] = maincards[num + s];
									maincards[num + s] = temp;

								}
							}
							hand = new Hand(maincards, sidecards, this);
						}
					}

				}
			}
		}

		return hand;

	}

	@Override
	public Hand getBestHand(Card[] allCards) {
		// TODO Auto-generated method stub

		ArrayList<int[]> subset = SubsetFinder.findSubsets(allCards.length, cardsRequired());
		ArrayList<Hand> handlst = new ArrayList<>();
		// Hand besthand = null;

		for (int i = 0; i < subset.size(); i++) {
			Hand hand = createHand(allCards, subset.get(i));
			if (hand != null) {
				handlst.add(hand);
			}
		}
		Hand[] arr = new Hand[handlst.size()];
		for (int j = 0; j < arr.length; j++) {
			arr[j] = handlst.get(j);
		}
		if (arr.length <= 0) {
			return null;
		}
		// Arrays.sort(arr);

		return arr[0];

//		for (int i = 0; i < subset.size(); i++) {
//			Hand hand = createHand(allCards, subset.get(i));
//			if (besthand == null || hand != null && hand.compareTo(besthand) > 0) {
//				besthand = hand;
//			}
//		}
//		return besthand;

	}

}
