package com.example.resizablerectangle;

import android.content.Context;
import android.graphics.Path;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    DataSender receiver;
    public int id = 1;
    CanvasOriginal DrawCanvas;

    public Fragment1(){
        receiver =new DataSender() {
            @Override
            public void ToSendPath(ArrayList<PathStorage> DrawnPathStorage, Path path, int id) {
                DrawCanvas.setPaintPath(path);
                DrawCanvas.setPathData(DrawnPathStorage);
                DrawCanvas.invalidate();

            }
        };
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        CanvasOriginal view= new CanvasOriginal(getContext(), MainActivity.Receiver, id);
        DrawCanvas = view;
        return view;
    }

    public DataSender getReceiver() {
        return receiver;
    }
}