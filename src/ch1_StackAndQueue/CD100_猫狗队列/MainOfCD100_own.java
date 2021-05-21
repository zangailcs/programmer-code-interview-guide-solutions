import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;

public class MainOfCD100_own {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        int opNum = Integer.parseInt(in.readLine());
        MyDogCatQueue dogCatQueue = new MyDogCatQueue();
        StringJoiner res = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < opNum; i++) {
            String line = in.readLine();
            if ("pollDog".equals(line)) {
                while (!dogCatQueue.isDogQueueEmpty()) {
                    MergePet dog = dogCatQueue.pollDog();
                    res.add(dog.getPetType() + " " + dog.getId());
                }
            } else if ("pollCat".equals(line)) {
                while (!dogCatQueue.isCatQueueEmpty()) {
                    MergePet cat = dogCatQueue.pollCat();
                    res.add(cat.getPetType() + " " + cat.getId());
                }
            } else if ("pollAll".equals(line)) {
                while (!dogCatQueue.isEmpty()) {
                    MergePet pet = dogCatQueue.pollAll();
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
                int id = Integer.parseInt(ss[2]);
                dogCatQueue.add(new MergePet(ss[1], id, count++));
            }
        }
        System.out.println(res.toString());
    }
}

class MergePet {
    private String type;
    private int id;
    private int count;

    public MergePet(String type, int id, int count) {
        this.type = type;
        this.id = id;
        this.count = count;
    }

    public String getPetType() {
        return this.type;
    }

    public int getId() {
        return this.id;
    }

    public int getCount() {
        return this.count;
    }
}



class MyDogCatQueue {
    private Queue<MergePet> dogQ;
    private Queue<MergePet> catQ;
    private long count;

    public MyDogCatQueue() {
        this.dogQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0;
    }

    public void add(MergePet pet) {
        if (pet.getPetType().equals("dog")) {
            this.dogQ.add(pet);
        } else if (pet.getPetType().equals("cat")) {
            this.catQ.add(pet);
        } else {
            throw new RuntimeException("err, not dog or cat");
        }
    }

    public MergePet pollAll() {
        if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
            if(this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                return this.dogQ.poll();
            } else {
                return this.catQ.poll();
            }
        } else if (!this.dogQ.isEmpty()) {
            return this.dogQ.poll();
        }  else if (!this.catQ.isEmpty()) {
            return this.catQ.poll();
        } else {
            throw new RuntimeException("err, queue is empty!");
        }
    }

    public MergePet pollDog() {
        return this.dogQ.poll();
    }

    public MergePet pollCat() {
        return this.catQ.poll();
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