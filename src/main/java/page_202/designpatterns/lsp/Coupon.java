package page_202.designpatterns.lsp;


class Item {

    private int count = 10;

    public int getCount() {
        return count;
    }
}

class SpecialItem extends Item {

    public int count = 50;

    public int getNumber() {
        return count;
    }
}

public class Coupon {


    public static void main(String[] args) {
        Item item = new Item();
        Item specialItem = new SpecialItem();
        SpecialItem sp = new SpecialItem();


        System.out.println(item.getCount());
        System.out.println(specialItem.getCount());
        System.out.println(sp.count);
        System.out.println(sp.getNumber());
        System.out.println(sp.getCount());
    }


}


