package Model;

public class Page {

    private String pageName;
    private String pageUrl;
    private String pageFullCategoryUrl;

    public Page(String pageName, String pageUrl) {
        this.pageName = pageName;
        this.pageUrl = pageUrl;
    }

    public Page(String pageName, String pageUrl, String pageFullCategoryUrl) {
        this.pageName = pageName;
        this.pageUrl = pageUrl;
        this.pageFullCategoryUrl = pageFullCategoryUrl;
    }

    public String getPageFullCategoryUrl() {
        return pageFullCategoryUrl;
    }

    public void setPageFullCategoryUrl(String pageFullCategoryUrl) {
        this.pageFullCategoryUrl = pageFullCategoryUrl;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageName='" + pageName + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                '}';
    }
}
