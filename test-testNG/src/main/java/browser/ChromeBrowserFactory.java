package browser;

import browser.Browser;

public class ChromeBrowserFactory implements BrowserFactory {
    @Override
    public Browser createBrowser() {
        return new ChromeBrowser();
    }
}
