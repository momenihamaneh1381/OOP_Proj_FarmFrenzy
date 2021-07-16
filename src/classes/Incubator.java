package classes;

public class Incubator extends Factory{
    public Incubator(  ) {
        super(0, 6, FactoryType.COMPLICATED, ProductType.EGG, ProductType.HEN , FactoryName.INCUBATOR);
    }
}
