package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmoothieTest {
    @Test
    public void classicSmoothie() {
        assertEquals("banana,honey,mango,peach,pineapple,strawberry",
                Smoothie.ingredients("Classic"));
    }

//    @Test
//    public void classicSmoothieWithoutStrawberry() {
//        assertEquals("banana,honey,mango,peach,pineapple",
//                Smoothie.ingredients("Classic,-strawberry"));
//    }
}