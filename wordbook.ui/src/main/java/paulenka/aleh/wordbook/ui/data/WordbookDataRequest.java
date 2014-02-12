package paulenka.aleh.wordbook.ui.data;

import paulenka.aleh.wordbook.data.util.Entity;

public class WordbookDataRequest extends Entity {

    private static final long serialVersionUID = 1L;

    private String filter;
    private int size;
    private int page;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}