package com.example.android.jokelibrary;

import java.util.Random;

public class JokeTeller {

    public String getAJoke() {

        //jokes are from http://redtri.com/best-jokes-for-kids/slide/1
        String[] jokes = {"What do you call a dinosaur that is sleeping?\n\nA dino-snore!",
                "What is fast, loud and crunchy? \n\nA rocket chip!",
                "Why did the teddy bear say no to dessert?\n\nBecause she was stuffed.",
                "What has ears but cannot hear?\n\nA cornfield.",
                "What did the left eye say to the right eye? \n\nBetween us, something smells!",
                "What do you get when you cross a vampire and a snowman?\n\nFrost bite!",
                "What did one plate say to the other plate?\n\nDinner is on me!",
                "Why did the student eat his homework?\n\nBecause the teacher told him it was a piece of cake!",
                "When you look for something, why is it always in the last place you look?\n\nBecause when you find it, you stop looking.",
                "What is brown, hairy and wears sunglasses?\n\nA coconut on vacation."};

        int randomIndex = new Random().nextInt(jokes.length);
        return jokes[randomIndex];
    }
}
