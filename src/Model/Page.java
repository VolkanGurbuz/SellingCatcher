package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;

public class Page {

    private String pageName;
    private String pageUrl;
    private String pageFullCategoryUrl;
    private ArrayList<Category> categoriesList = new ArrayList<>();

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

    public ArrayList<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }


    public void showAllCategories() {
        try {
            for (Category c :
                    categoriesList) {
                System.out.println(c.toString());
            }

        } catch (Exception e) {
            System.err.println("expection " + e);
        }
    }


    @Override
    public String toString() {
        return "Page{" +
                "pageName='" + pageName + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                '}';
    }
}
