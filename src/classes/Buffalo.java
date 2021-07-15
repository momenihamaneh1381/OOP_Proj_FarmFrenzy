package classes;

import javafx.scene.layout.Pane;

public class Buffalo extends DomesticAnimal{

    public Buffalo(Pane parent) {
        super(400, 5 , ProductType.MILK , AnimalType.BUFFALO , parent);
    }
}
