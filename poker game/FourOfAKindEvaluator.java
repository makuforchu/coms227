package hw4;

import api.Card;

/**
 * author makuforchu Evaluator for a hand containing (at least) four cards of
 * the same rank. The number of cards required is four.
 * 
 * The name of this evaluator is "Four of a Kind".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class FourOfAKindEvaluator extends AbstractEvaluator {
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking  ranking of this hand
	 * @param handSize number of cards in a hand
	 */
	public FourOfAKindEvaluator(int ranking, int handSize) {
		// TODO: call appropriate superclass constructor and
		// perform other initialization
		super(ranking, handSize, 4, "Four of a Kind");
	}

	@Override
	public boolean canSatisfy(Card[] mainCards) {
		// TODO Auto-generated method stub
//		Card num = null;
//		int count = 0;
//		for (int i = 0; i < mainCards.length; i++) {
//			num = mainCards[i];
//			for (int j = i; j < mainCards.length; j++) {
//				if (mainCards[i] == mainCards[j]) {
//					count++;
//					if (count == 4) {
//						return true;
//					}
//				}
//			}
		// }
		return (mainCards.length == cardsRequired() && mainCards[0].compareToIgnoreSuit(mainCards[1]) == 0
				&& mainCards[1].compareToIgnoreSuit(mainCards[2]) == 0
				&& mainCards[2].compareToIgnoreSuit(mainCards[3]) == 0);

	}

}
