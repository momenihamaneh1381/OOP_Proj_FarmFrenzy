public class Product implements Turn{
    int x;
    int y;
    int price;
    int capacity;
    int max_time;
    int time;
    ProductType productType;

    public Product(ProductType productType) {
        this.productType = productType;
    }

    public Product(ProductType productType , int x , int y) {
        this.x = x;
        time=0;
        this.y=y;
        this.productType = productType;
        switch (productType){
            case EGG:
                capacity=1;
                max_time =4;
                price=15;
                break;
            case MILK:
                capacity=1;
                max_time =4;
                price=25;
                break;
            case WING:
                capacity=1;
                max_time =4;
                price=20;
                break;
            case POWDER:
                capacity=2;
                max_time =5;
                price=40;
                break;
            case FABRIC:
                capacity=2;
                max_time =5;
                price=50;
                break;
            case POCKET_MILK:
                capacity=2;
                max_time =5;
                price=60;
                break;
            case BREAD:
                capacity=4;
                max_time =6;
                price=80;
                break;
            case CLOTH:
                capacity=4;
                max_time =6;
                price=100;
                break;
            case ICE_CREAM:
                capacity=4;
                max_time =6;
                price=120;
                break;
        }
    }

    @Override
    public void turn() {
        time++;
    }
}
