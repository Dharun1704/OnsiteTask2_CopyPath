package com.example.resizablerectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment2 extends Fragment {

    int id = 2;
    DataSender receiver;
    CanvasOriginal DrawCanvas;

    public Fragment2(){
        receiver = new DataSender() {
            @Override
            public void ToSendPath(ArrayList<PathStorage> DrawnPathStorage, Path path, int id) {
                DrawCanvas.setPaintPath(path);
                DrawCanvas.setPathData(DrawnPathStorage);
            }
        };
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CanvasOriginal FragmentView= new CanvasOriginal(getContext(), MainActivity.Receiver, id);
        DrawCanvas = FragmentView;
        return FragmentView;
    }
    public DataSender getReceiver(){
        return receiver;

    }

}