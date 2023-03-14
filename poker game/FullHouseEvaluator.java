package hw4;

import api.Card;

/**
 * author makuforchu Evaluator for a generalized full house. The number of
 * required cards is equal to the hand size. If the hand size is an odd number
 * n, then there must be (n / 2) + 1 cards of the matching rank and the
 * remaining (n / 2) cards must be of matching rank. In this case, when
 * constructing a hand, the larger group must be listed first even if of lower
 * rank than the smaller group</strong> (e.g. as [3 3 3 5 5] rather than [5 5 3
 * 3 3]). If the hand size is even, then half the cards must be of matching rank
 * and the remaining half of matching rank. Any group of cards, all of which are
 * the same rank, always satisfies this evaluator.
 * 
 * The name of this evaluator is "Full House".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class FullHouseEvaluator extends AbstractEvaluator {
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking  ranking of this hand
	 * @param handSize number of cards in a hand
	 */
	public FullHouseEvaluator(int ranking, int handSize) {
		// TODO: call appropriate superclass constructor and
		// perform other initialization

		super(ranking, handSize, handSize, "Full House");
	}

	@Override
	public boolean canSatisfy(Card[] mainCards) {
		// TODO Auto-generated method stub

		boolean results = false;
		if (cardsRequired() == handSize()) {
			Card num1 = mainCards[0];
			Card num2 = null;
			int count1 = 0;
			int count2 = 0;
			int temp = 0;

			for (int i = 0; i < mainCards.length; i++) {
				if (mainCards[i].compareToIgnoreSuit(num1) != 0) {
					num2 = mainCards[i];
					break;
				} else {
					temp++;
					if (temp == mainCards.length) {
						results = true;
						break;
					}
				}
			}
			for (int j = 0; j < mainCards.length; j++) {
				if (mainCards[j].compareToIgnoreSuit(num1) == 0) {
					count1++;
				} else if (mainCards[j].compareToIgnoreSuit(num2) == 0) {
					count2++;
				} else {
					results = false;
					break;
				}
			}

			if (handSize() % 2 == 1) {
				if (count1 > count2) {
					if ((handSize() / 2) + 1 == count1) {
						results = true;

					}
				} else if (count2 > count1) {
					if ((handSize() / 2) + 1 == count2) {
						results = true;
					}
				}
			} else {
				if (count1 == count2) {
					results = true;
				}
			}
		}
		return results;
	}
}
