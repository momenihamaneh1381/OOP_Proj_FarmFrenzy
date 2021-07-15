package classes;


import javafx.scene.layout.Pane;

public class Bear extends WildAnimal{
    public Bear(Pane parent) {
        super(4, 1, 400 , AnimalType.BEAR , parent);
    }
}
