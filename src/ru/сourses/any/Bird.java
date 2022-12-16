package ru.сourses.any;

class Bird {
    public void sing(String song){
        System.out.println(song);
    }
}

class Sparrow extends Bird implements Birdable{
    public void sing() {
        System.out.println("чырык");
    }
}

class Cuckoo extends Bird implements Birdable{
    public void sing(){
        int r = (int) (Math.random() * 9);
        for(int i=0; i <=r; i++){
            sing("ку-ку");
        }
    }
}

class Parrot extends Bird implements Birdable{
    String song;

    public Parrot(String song){
        this.song = song;
    }

    public void sing(){
        int r = (int) (Math.random() * this.song.length());
        String[] ch = this.song.split("");
        String tmp = ch[r];
        ch[r] = ch[this.song.length() - 1 - r];
        ch[this.song.length() - 1 - r] = tmp;
        sing(String.join("",ch));
    }

}

interface Birdable{
    void sing();
}
