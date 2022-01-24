package com.vaca.snake3d

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mGLSurfaceView: GLSurfaceView
    private lateinit var renderer: CubeRenderer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        mGLSurfaceView = findViewById(R.id.gl)

        renderer = CubeRenderer()
        mGLSurfaceView.run {
            // setOnTouchListener(gaga)
            setEGLContextClientVersion(3)
            setRenderer(renderer)
            renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        }
    }
}