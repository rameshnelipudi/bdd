package Pages;

import net.serenitybdd.core.pages.PageObject;

import java.util.Random;

/**
 * Created by E001489 on 02-01-2017.
 */
public class BasePage extends PageObject {
    public int generateRandom() {
        Random r = new Random(System.currentTimeMillis());
        return r.nextInt(2000000000);
    }
}
