package Model;

public class Page {

    private String pageName;
    private String pageUrl;

    public Page(String pageName, String pageUrl) {
        this.pageName = pageName;
        this.pageUrl = pageUrl;
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
