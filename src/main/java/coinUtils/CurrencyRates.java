package coinUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/***
 * The class gets conversions rates of currency using a REST api.
 */
public class CurrencyRates {

    /***
     * Returns the currency conversion value from base coin to desired coin.
     * @param base The currency to convert from.
     * @param desired The currency to convert to.
     * @param amount amount of currency to convert.
     * @return returns the currency conversion value.
     * @throws IOException
     * @throws InterruptedException
     */
    public static double getCurrencyConversion(Coins base, Coins desired, double amount) throws IOException, InterruptedException {
        double conversionValue;
        StringBuilder getRequest = new StringBuilder();
        getRequest.append("https://api.currencyscoop.com/v1/convert");
        getRequest.append("?api_key=d42128ef1ad4eeaeec81a165b9277fb9");
        getRequest.append("&base=").append(base);
        getRequest.append("&to=").append(desired);
        getRequest.append("&amount=").append(amount);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getRequest.toString())).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String responseStr = response.body();
        responseStr = responseStr.substring(responseStr.lastIndexOf(":") + 1);
        responseStr = responseStr.substring(0, responseStr.indexOf("}"));
        conversionValue = Double.parseDouble(responseStr);

        return conversionValue;
    }
}
