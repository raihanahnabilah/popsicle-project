package com.example.popsicle;

import android.view.SurfaceView;

import com.example.popsicle.models.Universe;
import com.example.popsicle.rendering.GraphicsRenderer;

public class MainController {
    private final SurfaceView sv;
    private final Universe universe;
    private final GraphicsRenderer graphicsRenderer;

    public MainController(SurfaceView sv){
        this.sv = sv;
        this.universe = new Universe();
        this.graphicsRenderer = new GraphicsRenderer(this.universe, this.sv.getResources());
        this.universe.setCallBack(this.graphicsRenderer);
        this.sv.setWillNotDraw(false);
        this.sv.getHolder().addCallback(this.graphicsRenderer);
    }

}
