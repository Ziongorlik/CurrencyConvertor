package coinTypes;

import coinUtils.ICalculate;

/***
 * The class represents Euro
 * {@link Coin}
 */
public class EUR extends Coin implements ICalculate {
    private final double value = 4.23; // 1 ILS = 4.23 EUR

    /***
     * Returns the hard coded value of EUR amount that equals 1 ILS.
     * @return the hard coded value.
     */
    @Override
    double getValue() {
        return this.value;
    }

    /***
     * Calculates the multiplication of a value by quantity
     * @param amount amount of ILS needed to convert
     * @return returns the result of the value multiplied by the amount
     */
    @Override
    public double calculate(double amount) {
        return getValue() * amount;
    }
}
