package com.example.resizablerectangle;

import android.graphics.Path;

import java.util.ArrayList;

public interface DataSender {
    void ToSendPath(ArrayList<PathStorage> DrawnPathStorage, Path path, int id);
}