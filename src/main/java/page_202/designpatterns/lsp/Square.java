package page_202.designpatterns.lsp;

public class Square extends Rectangle {

    private int width;

    private int height;

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}
