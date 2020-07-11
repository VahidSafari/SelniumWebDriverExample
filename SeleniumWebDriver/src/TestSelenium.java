import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestSelenium {
    private FirefoxDriver driver;
    private HashMap<String, List<String>> deprecatedAttributes;

    TestSelenium(String url) {
        driver = new FirefoxDriver();
        driver.get(url);
    }

    void testTableHeader() {
        List<WebElement> tables = driver.findElements(By.cssSelector("table"));
        if (!tables.isEmpty()) {
            for (WebElement t : tables) {
                if (t.findElement(By.cssSelector("th")) == null) {
                    System.out.println("No header find for tables");
                    break;
                }
            }
        } else {
            System.out.println("There is no table");
        }
    }

    void checkLangAttributesExistenceInHtmlTags() {
        try {
            WebElement element = driver.findElement(By.tagName("html"));
            String lang = element.getAttribute("lang");
            String xmlLang = element.getAttribute("xml:lang");
            if (isNullOrEmpty(lang) && isNullOrEmpty(xmlLang)) {
                System.err.println("\nlanguage attribute not defined in the html tag");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, List<String>> getDeprecatedHTML5Attributes() {
        if (deprecatedAttributes != null) {
            deprecatedAttributes = new HashMap<>();
            deprecatedAttributes.put("table", new ArrayList<>());
            return deprecatedAttributes;
        } else return deprecatedAttributes;
    }

    void checkTableDescription() {
        List<WebElement> tableElements = driver.findElements(By.tagName("table"));
        for (WebElement element : tableElements) {
            if (!isNullOrEmpty(element.getAttribute("role")) &&
                    !element.getAttribute("role").equals("presentation") &&
                    !element.getAttribute("role").equals("none") ||
                    !isNullOrEmpty(element.getAttribute("aria-hidden")) &&
                            !element.getAttribute("aria-hidden").equals("true")) {
                try {
                    element.findElement(By.tagName("caption"));
                } catch (NoSuchElementException e) {
                    if (isNullOrEmpty(element.getAttribute("aria-describedby")) && isNullOrEmpty(element.getAttribute("summary"))) {
                        try {
                            WebElement parentElement = element.findElement(By.xpath("./.."));
                            if (!parentElement.getTagName().equals("figure") || parentElement.findElement(By.tagName("figcaption")) == null)
                                throw new NoSuchElementException("parent tag is not figure");
                        } catch (NoSuchElementException parentException) {
                            System.out.println("no description found for table");
                        }
                    }
                }
            }
        }
    }


    void checkLinkTarget() {
        List<WebElement> linkElements = driver.findElements(By.tagName("a"));
        mainLoop:
        for (int i = 0; i < linkElements.size(); i++) {
            String currentLink = linkElements.get(i).getAttribute("href");
            String currentValue = linkElements.get(i).getText();
            for (int j = i; j < linkElements.size(); j++) {
                if (linkElements.get(j).getText().equals(currentValue)) {
                    if (!linkElements.get(j).getAttribute("href").equals(currentLink)) {
                        System.out.println("Same text for different targets in link");
                        break mainLoop;
                    }
                }
            }
        }
    }

    private static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "G:\\geckodriver.exe");
        String url = "file:///C:/Users/Vahid/Desktop/tabledescription.html";
        TestSelenium test = new TestSelenium(url);
//        test.checkLangAttributesExistenceInHtmlTags();
//        test.checkTableDescription();
        test.checkLinkTarget();
    }

}
