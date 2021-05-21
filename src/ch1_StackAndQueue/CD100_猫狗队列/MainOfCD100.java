import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringJoiner;

public class MainOfCD100 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int opNum = Integer.parseInt(in.nextLine());
        DogCatQueue dogCatQueue = new DogCatQueue();
        StringJoiner res = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < opNum; i++) {
            String line = in.nextLine();
            if ("pollDog".equals(line)) {
                while (!dogCatQueue.isDogQueueEmpty()) {
                    Dog dog = dogCatQueue.pollDog();
                    res.add(dog.getPetType() + " " + dog.getId());
                }
            } else if ("pollCat".equals(line)) {
                while (!dogCatQueue.isCatQueueEmpty()) {
                    Cat cat = dogCatQueue.pollCat();
                    res.add(cat.getPetType() + " " + cat.getId());
                }
            } else if ("pollAll".equals(line)) {
                while (!dogCatQueue.isEmpty()) {
                    Pet pet = dogCatQueue.pollAll();
                    res.add(pet.getPetType() + " " + pet.getId());
                }
            } else if ("isEmpty".equals(line)) {
                res.add(dogCatQueue.isEmpty() ? "yes" : "no");
            } else if ("isDogEmpty".equals(line)) {
                res.add(dogCatQueue.isDogQueueEmpty() ? "yes" : "no");
            } else if ("isCatEmpty".equals(line)) {
                res.add(dogCatQueue.isCatQueueEmpty() ? "yes" : "no");
            } else {
                // add
                String[] ss = line.split(" ");
                String type = ss[1];
                int id = Integer.parseInt(ss[2]);
                if ("dog".equals(type)) {
                    dogCatQueue.add(new Dog(id));
                } else {
                    dogCatQueue.add(new Cat(id));
                }
            }
        }
        System.out.println(res.toString());
    }
}

class Pet {
    private String type;
    private int id;

    public Pet(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public String getPetType() {
        return this.type;
    }

    public int getId() {
        return this.id;
    }
}

class Dog extends Pet {
    public Dog(int id) {
        super("dog", id);
    }
}

class Cat extends Pet {
    public Cat(int id) {
        super("cat", id);
    }
}

class PetEnterQueue {
    private Pet pet;
    private long count;

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return this.pet;
    }

    public long getCount() {
        return this.count;
    }

    public String getEnterPetType() {
        return this.pet.getPetType();
    }
}

class DogCatQueue {
    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    private long count;

    public DogCatQueue() {
        this.dogQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("dog")) {
            this.dogQ.add(new PetEnterQueue(pet, this.count++));
        } else if (pet.getPetType().equals("cat")) {
            this.catQ.add(new PetEnterQueue(pet, this.count++));
        } else {
            throw new RuntimeException("err, not dog or cat");
        }
    }

    public Pet pollAll() {
        if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
            if(this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                return this.dogQ.poll().getPet();
            } else {
                return this.catQ.poll().getPet();
            }
        } else if (!this.dogQ.isEmpty()) {
            return this.dogQ.poll().getPet();} else if (!this.catQ.isEmpty()) {
            return this.catQ.poll().getPet();
        } else {
            throw new RuntimeException("err, queue is empty!");
        }
    }

    public Dog pollDog() {
        if (!this.isDogQueueEmpty()) {
            return (Dog) this.dogQ.poll().getPet();
        } else {
            throw new RuntimeException("Dog queue is empty!");
        }
    }

    public Cat pollCat() {
        if (!this.isCatQueueEmpty()) {
            return (Cat) this.catQ.poll().getPet();
        } else
            throw new RuntimeException("Cat queue is empty!");
    }

    public boolean isEmpty() {
        return this.dogQ.isEmpty() && this.catQ.isEmpty();
    }

    public boolean isDogQueueEmpty() {
        return this.dogQ.isEmpty();
    }

    public boolean isCatQueueEmpty() {
        return this.catQ.isEmpty();
    }
}