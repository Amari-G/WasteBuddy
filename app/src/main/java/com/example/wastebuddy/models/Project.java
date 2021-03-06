package com.example.wastebuddy.models;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

@ParseClassName("Project")
public class Project extends ParseObject {

    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_STEPS = "steps";
    public static final String KEY_DIFFICULTY= "difficulty";
    public static final String KEY_LIKES= "likesCount";
    public static final String KEY_ITEMS= "items";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_AUTHOR = "author";

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public String getSteps() {
        return getString(KEY_STEPS);
    }

    public void setSteps(List<String> steps) {
        put(KEY_STEPS, steps);
    }

    public String getDifficulty() {
        return getString(KEY_DIFFICULTY);
    }

    public void setDifficulty(String difficulty) {
        put(KEY_DIFFICULTY, difficulty);
    }

    public int getLikes() {
        return getInt(KEY_LIKES);
    }

    public void like() {
        increment(KEY_LIKES);
        saveInBackground();
    }

    public void unlike() {
        increment(KEY_LIKES, -1);
        saveInBackground();
    }

    public List getItems() {
        return getList(KEY_ITEMS);
    }

    public void setItems (List items) {
        put(KEY_ITEMS, items);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }

    public ParseUser getAuthor() {
        return getParseUser(KEY_AUTHOR);
    }

    public void setAuthor(ParseUser author) {
        put(KEY_AUTHOR, author);
    }

}
