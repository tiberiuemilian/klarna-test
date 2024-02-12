package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Smoothie {
    public static final String CLASSIC = "Classic";
    public static final String FREEZIE = "Freezie";
    public static final String GREENIE = "Greenie";
    public static final String JUST_DESSERTS = "Just Desserts";

    public static String ingredients(String order) {
        System.out.println(order);
        String[] classic = {"strawberry", "banana", "pineapple", "mango", "peach", "honey"};
        String[] freezie = {"blackberry", "blueberry", "black currant", "grape juice", "frozen yogurt"};
        String[] greenie = {"green apple", "lime", "avocado", "spinach", "ice", "apple juice"};
        String[] just_desserts = {"banana", "ice cream", "chocolate", "peanut", "cherry"};
        String[] menu = {CLASSIC, FREEZIE, GREENIE, JUST_DESSERTS};

        if (order == null || order.equals("")) {
            throw new IllegalArgumentException();
        }

        String words[] = order.split(",");


        if (words.length < 1) return "";

        String menuSelection = words[0];
        int ingredientsIdx = 1;
        List<String> menuIngredients = null;
        switch (menuSelection) {
            case CLASSIC:
                menuIngredients = List.of(classic);
                break;
            case FREEZIE:
                menuIngredients = List.of(freezie);
                break;
            case GREENIE:
                menuIngredients = List.of(greenie);
                break;
            case JUST_DESSERTS:
                menuIngredients = List.of(just_desserts);
                break;
            default:
                throw new IllegalArgumentException();
        }

        List<String> ingredients = Arrays.asList(Arrays.copyOfRange(words, ingredientsIdx, words.length));
        List<String> newIngredients = ingredients.stream()
                .filter(ingredient -> !ingredient.startsWith("-"))
                .collect(Collectors.toList());

        if (newIngredients.size() > 0) {
            throw new IllegalArgumentException();
        }

        List allergens =
                ingredients.stream()
                        .filter(ingredient -> ingredient.startsWith("-"))
                        .map(ingredient -> ingredient.replaceFirst("^-", ""))
                        .collect(Collectors.toList());

        return
                menuIngredients.stream()
                        .filter(ingredient -> !allergens.contains(ingredient))
                        .sorted()
                        .collect(Collectors.joining(","));

    }
}
