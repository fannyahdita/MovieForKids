package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import java.util.jar.Attributes

class SplashGLSurfaceView(context: Context, attrs: AttributeSet) : GLSurfaceView(context, attrs) {

    private val renderer: SplashGLRenderer

    init {

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = SplashGLRenderer(context)

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer)
    }
}