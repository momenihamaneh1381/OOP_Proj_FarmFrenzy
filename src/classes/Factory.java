package classes;
public class Factory implements Turn{
    int price;
    int max_time;
    int time;
    int level;
    int numOfProduct;
    boolean work;

    public int getPrice() {
        return price;
    }

    public boolean isBiuld;
    FactoryType factoryType;
    ProductType productTypeInput;
    ProductType productTypeOutput;
    FactoryName factoryName;

    public Factory(int price, int max_time, FactoryType factoryType, ProductType productTypeInput, ProductType productTypeOutput , FactoryName factoryName) {
        this.price = price;
        time= 0;
        level = 1;
        isBiuld = false;
        numOfProduct=1;
        this.max_time = max_time;
        this.factoryType = factoryType;
        this.productTypeInput = productTypeInput;
        this.productTypeOutput = productTypeOutput;
        this.factoryName = factoryName;
        work = false;
//        switch (productTypeInput){
//            case EGG:
//                factoryName=FactoryName.EGG_POWDER_PLANT;
//                break;
//            case WING:
//                factoryName=FactoryName.WEAVING_FACTORY;
//                break;
//            case MILK:
//                factoryName=FactoryName.POCKET_MILK_FACTORY;
//                break;
//            case POWDER:
//                factoryName=FactoryName.BAKERY;
//                break;
//            case FABRIC:
//                factoryName=FactoryName.SEWING_FACTORY;
//                break;
//            case POCKET_MILK:
//                factoryName=FactoryName.ICECREAM_FACTORY;
//                break;
//        }
    }

    public int getLevel() {
        return level;
    }

    public void upgrade(){
        if (level==1)
        level++;
    }

    @Override
    public void turn() {
        if (work)
        time++;
    }
}
