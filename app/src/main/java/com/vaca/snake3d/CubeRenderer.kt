package com.vaca.snake3d

import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import com.vaca.c60.utils.ResReadUtils.readResource
import com.vaca.c60.utils.ShaderUtils.compileFragmentShader
import com.vaca.c60.utils.ShaderUtils.compileVertexShader
import com.vaca.c60.utils.ShaderUtils.linkProgram


import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL
import javax.microedition.khronos.opengles.GL10
import kotlin.random.Random


class CubeRenderer : GLSurfaceView.Renderer {
    private val vertexBuffer: FloatBuffer



    private var mProgram = 0
    private val InitMatrix = FloatArray(16)
    private val rotationMatrix = FloatArray(16)
    private val rotationMatriy = FloatArray(16)
    private val rotationMatriz = FloatArray(16)

    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    private val vPMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)
    var angleX = 0f
    var angleY = 0f
    var angleZ = 0f

    /**
     * 点的坐标
     */


    var ss = 0
    lateinit var vv: FloatArray
    private var vertexPoints = floatArrayOf(
        0.0f, 0.5f, 0.0f,
        -0.5f, -0.5f, 0.0f,
        0.5f, -0.5f, 0.0f
    )

    /**
     * 定义索引
     */
    private var indices = ShortArray(20 * 4 * 3 + 12 * 3 * 3)



    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {

        //设置背景颜色
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        //编译
        val vertexShaderId = compileVertexShader(readResource(R.raw.vertex_colorcube_shader))
        val fragmentShaderId = compileFragmentShader(readResource(R.raw.fragment_colorcube_shader))
        //链接程序片段
        mProgram = linkProgram(vertexShaderId, fragmentShaderId)
        //使用程序片段
        GLES30.glUseProgram(mProgram)
        GLES30.glVertexAttribPointer(
            0,
            VERTEX_POSITION_SIZE,
            GLES30.GL_FLOAT,
            false,
            0,
            vertexBuffer
        )
        //启用位置顶点属性
        GLES30.glEnableVertexAttribArray(0)

        GLES30.glClearDepthf(1.0f); // 设置深度缓存
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);    // 启用深度测试
        GLES30.glDepthFunc(GLES30.GL_LEQUAL);     // 深度测试类型

        Matrix.setIdentityM(InitMatrix, 0)
    }


    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)
        val ratio: Float = width.toFloat() / height.toFloat()
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 1f, 7f)
    }

    override fun onDrawFrame(gl: GL10) {
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT)
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)
        GLES30.glClearColor(1f,1f,1f,1f)
        GLES30.glDrawArrays(
            GLES30.GL_TRIANGLES,
            0,
            3
        )
    }

    companion object {
        private const val VERTEX_POSITION_SIZE = 3
        private const val VERTEX_COLOR_SIZE = 4
    }

    init {



        vertexBuffer = ByteBuffer.allocateDirect(vertexPoints.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer().apply {
                put(vertexPoints)
                position(0)
            }


    }
}