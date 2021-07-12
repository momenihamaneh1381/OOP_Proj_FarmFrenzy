package classes;
public class Factory implements Turn{
    int price;
    int max_time;
    int time;
    int level;
    int numOfProduct;
    boolean work;
    FactoryType factoryType;
    ProductType productTypeInput;
    ProductType productTypeOutput;
    FactoryName factoryName;

    public Factory(int price, int max_time, FactoryType factoryType, ProductType productTypeInput, ProductType productTypeOutput) {
        this.price = price;
        time= 0;
        level = 1;
        numOfProduct=1;
        this.max_time = max_time;
        this.factoryType = factoryType;
        this.productTypeInput = productTypeInput;
        this.productTypeOutput = productTypeOutput;
        work = false;
        switch (productTypeInput){
            case EGG:
                factoryName=FactoryName.EGG_POWDER_PLANT;
                break;
            case WING:
                factoryName=FactoryName.WEAVING_FACTORY;
                break;
            case MILK:
                factoryName=FactoryName.POCKET_MILK_FACTORY;
                break;
            case POWDER:
                factoryName=FactoryName.BAKERY;
                break;
            case FABRIC:
                factoryName=FactoryName.SEWING_FACTORY;
                break;
            case POCKET_MILK:
                factoryName=FactoryName.ICECREAM_FACTORY;
                break;
        }
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
