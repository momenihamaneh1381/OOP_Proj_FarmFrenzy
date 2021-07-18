package classes;

import animations.*;

public class Factory implements Turn{
    int price;
    int max_time;
    int time;
    public int level;
    int numOfProduct;
    public boolean work;

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
    }

    public int getLevel() {
        return level;
    }

    public void upgrade(){
        if (level==1){
        level++;
        switch (factoryName){
            case EGG_POWDER_PLANT:
               EggpowderTransition.level++;
               break;
            case WEAVING_FACTORY:
                WeavingTransition.level++;
                break;
            case BAKERY:
                BakeryTransition.level++;
                break;
            case SEWING_FACTORY:
                SewingTransition.level++;
                break;
            case ICECREAM_FACTORY:
                IcecreamTransition.level++;
                break;
            case POCKET_MILK_FACTORY:
                PocketTransition.level++;
        }
        }
    }

    @Override
    public void turn() {
        if (work)
        time++;
    }
}
