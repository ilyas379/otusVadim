public class FireFoxBrowserFactory implements BrowserFactory {
    @Override
    public Browser createBrowser() {
        return new FireFoxBrowser();
    }
}
