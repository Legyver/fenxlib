package it.core.i18n;

import com.legyver.fenxlib.api.i18n.ResourceBundleServiceRegistry;
import com.legyver.fenxlib.tests.base.FenxlibTest;
import com.legyver.fenxlib.tests.base.config.annotation.FenxlibConfiguration;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@FenxlibConfiguration(resourceBundles = {
        "it.core.i18n.test"
})
public class ResourceBundleServiceTest extends FenxlibTest {

    @Test
    public void valueLocaleSpecific() {
        String result = ResourceBundleServiceRegistry.getInstance().getMessage("only.here");
        assertThat(result).isEqualTo("Only here");
    }

    @Test
    public void en() {
        String result = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.ENGLISH, "general.english");
        assertThat(result).isEqualTo("General English");

        result = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.US, "general.english");
        assertThat(result).isEqualTo("General English");

        result = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.UK, "general.english");
        assertThat(result).isEqualTo("General English");
    }

    @Test
    public void en_unspecified() {
        //english without a country should pull the general english code
        String result = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.ENGLISH, "requires.auth");
        assertThat(result).isEqualTo("Requires Auth");

        //because we don't have an _en_CA override for this property, expect to get the general english one
        Locale canadianEnglish = Locale.CANADA;
        result = ResourceBundleServiceRegistry.getInstance().getMessage(canadianEnglish, "requires.auth");
        assertThat(result).isEqualTo("Requires Auth");

        //because we don't have an _en_IE override for this property, expect to get the general english one
        Locale irishEnglish = Locale.forLanguageTag("en-IE");
        result = ResourceBundleServiceRegistry.getInstance().getMessage(irishEnglish, "requires.auth");
        assertThat(result).isEqualTo("Requires Auth");
    }

   @Test
    public void en_GB() {
        Locale locale = Locale.forLanguageTag("en-GB");
        String result = ResourceBundleServiceRegistry.getInstance().getMessage(locale, "requires.auth");
        assertThat(result).isEqualTo("Requires Authorisation");
   }

   @Test
   public void en_US() {
       Locale locale = Locale.forLanguageTag("en-US");
       String result = ResourceBundleServiceRegistry.getInstance().getMessage(locale, "requires.auth");
       assertThat(result).isEqualTo("Requires Authorization");
   }

   @Test
   public void es() {
       Locale locale = new Locale("es");
       String result = ResourceBundleServiceRegistry.getInstance().getMessage(locale, "requires.auth");
       assertThat(result).isEqualTo("Requiere autorización");
   }

   @Test
   public void germany() {
       String result = ResourceBundleServiceRegistry.getInstance().getMessage(Locale.GERMANY, "requires.auth");
       assertThat(result).isEqualTo("Erforderliche Zulassung");
   }

}
