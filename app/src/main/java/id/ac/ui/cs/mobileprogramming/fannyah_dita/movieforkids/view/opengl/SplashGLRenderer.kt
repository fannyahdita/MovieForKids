package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.opengl

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class SplashGLRenderer(val context: Context) : GLSurfaceView.Renderer {

    private lateinit var mSquare1: Square
    private lateinit var mSquare2: Square

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        // Set the background frame color
        GLES20.glClearColor(235f, 161f, 161f, 0f)
        // initialize a triangle

        var squareCoords1 = floatArrayOf(
            -0.5f, -0.5f, 0.0f, //left-bottom
            0.5f, -0.5f, 0.0f, //right-bottom
            -0.5f, 0.5f, 0.0f, //left-top
            0.5f, 0.5f, 0.0f //right-top
        )
        val color1 = floatArrayOf(0f, 0f, 0f, 0f)

        var squareCoords2 = floatArrayOf(
            -0.4f, 0.4f, 0.0f,      // top left
            -0.4f, -0.4f, 0.0f,      // bottom left
            0.4f, -0.4f, 0.0f,      // bottom right
            0.4f, 0.4f, 0.0f       // top right
        )
        val color2 = floatArrayOf(207f, 207f, 207f, 1.0f)

        mSquare1 = Square(squareCoords1, color1)
        mSquare2 = Square(squareCoords2, color2)
    }

    override fun onDrawFrame(unused: GL10) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        mSquare1.draw()
        mSquare2.draw()
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

}