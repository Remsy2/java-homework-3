import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws WrongFoodException {
        Aviary<Predator> myAviary = new Aviary<>(AviarySize.MID);
        Predator myAnimal = new Predator("Lion", "Pussy", AviarySize.MID);
        myAviary.setAnimal(myAnimal, "Puss");
        Predator temp = myAviary.getAnimal("Puss");
        System.out.println("Hi! " + temp.getName() + ' ' + temp.getNickname());
        myAviary.delAnimal("Puss");
        temp = myAviary.getAnimal("Puss");
        Aviary<Herbivore> newAviary = new Aviary<>(AviarySize.MID);
        Herbivore newAnimal = new Herbivore("Elephant", "Carlo", AviarySize.LARGE);
        newAviary.setAnimal(newAnimal, "Carlo");
        newAnimal.eat("Plants");
        newAnimal.eat("Meat");
    }
}


enum AviarySize {
    MICRO(0),
    SMALL(1),
    MID(2),
    LARGE(3);

    private int value;
    private AviarySize(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

abstract class Animal {
    protected String name;
    protected String nickname;
    protected AviarySize aviarySize = AviarySize.MID;

    public int hashCode() {
        int result = nickname == null ? 0 : nickname.hashCode();
        return 31 * result;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Animal o = (Animal)obj;
        return (name == o.name) && (nickname == o.nickname);
    }
    public String getName() {
        return name;
    }
    public String getNickname() {
        return nickname;
    }
    public AviarySize getAviarySize() {
        return aviarySize;
    }
    public abstract void eat(String food) throws WrongFoodException;
}

class Predator extends Animal {
    public Predator(String name, String nickname, AviarySize aviarySize) {
        System.out.println("Creating " + name + ' ' + nickname);
        this.name = name;
        this.nickname = nickname;
        this.aviarySize = aviarySize;
    }
    public void eat(String food) throws WrongFoodException {
        if(food.equals("Meat"))
            System.out.println("Eating...");
        else
            throw new WrongFoodException("Predator only eat meat!");
    }
}

class Herbivore extends Animal {
    public Herbivore(String name, String nickname, AviarySize aviarySize) {
        System.out.println("Creating " + name + ' ' + nickname);
        this.name = name;
        this.nickname = nickname;
        this.aviarySize = aviarySize;
    }
    public void eat(String food) throws WrongFoodException {
        if(food.equals("Plants"))
            System.out.println("Eating...");
        else
            throw new WrongFoodException("Herbivore only eat plants!");
    }
}

class WrongFoodException extends Exception {
    public WrongFoodException(String message) {
        super(message);
    }
}

class Aviary<T extends Animal> {
    private AviarySize size;
    private HashMap<String, T> Map = new HashMap<>();

    public Aviary(AviarySize size) {
        this.size = size;
    }
    public void setAnimal(T obj, String nickname) {
        if(obj.aviarySize.getValue() <= size.getValue()) {
            System.out.println("Putting animal " + nickname + " to aviary...");
            Map.put(nickname, obj);
        }
        else
            System.out.println("Unsuitable aviary size!");
    }
    public T getAnimal(String nickname) {
        System.out.println("Searching for animal " + nickname + "...");
        T result =  Map.get(nickname);
        if(result != null)
            System.out.println("Getting animal from aviary...");
        else
            System.out.println("Can't find " + nickname);
        return result;
    }
    public void delAnimal(String nickname) {
        System.out.println("Removing animal " + nickname + " from aviary...");
        Map.remove(nickname);
    }
    public AviarySize getAviarySize() {
        return size;
    }
}