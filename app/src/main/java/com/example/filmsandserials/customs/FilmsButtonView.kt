package com.example.filmsandserials.customs

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.filmsandserials.R

class FilmsButtonView(context: Context, attrs: AttributeSet): View(context, attrs) {
    private val painter = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
        strokeWidth = 10f
    }
    private var typeCircle: Int

    init {
        context.obtainStyledAttributes(attrs, R.styleable.FilmsButtonView).apply {
            try {
                typeCircle = getInt(R.styleable.FilmsButtonView_circle_type, 0)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawHalfCircle(canvas)
    }

    private fun drawHalfCircle(canvas: Canvas?) {
        val oval = RectF()
        val center_x = width / 2f
        val center_y = height

        val radius = width / 2.5f
        oval.set(center_x - radius,
            10f,
            center_x + radius,
            center_y + radius)

        if (typeCircle == 0) {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.films_button)
            painter.shader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            canvas?.drawArc(oval, 180f, 180f, true, painter)
            painter.shader = null
            painter.style = Paint.Style.STROKE
            canvas?.drawArc(oval, 180f, 180f, true, painter)
            drawTextInCircle(canvas, false)

        } else {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.serials_button)
            painter.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT)
            canvas?.rotate(180f, width / 2f, 170f)
            canvas?.drawArc(oval, 180f, 180f, true, painter)
            painter.shader = null
            painter.style = Paint.Style.STROKE
            canvas?.drawArc(oval, 180f, 180f, true, painter)
            drawTextInCircle(canvas, true)
        }
    }

    private fun drawTextInCircle(canvas: Canvas?, reverse: Boolean) {
        painter.apply {
            painter.shader = null
            textSize = 46f
            isUnderlineText = true
            style = Paint.Style.FILL
            color = Color.RED
            isFakeBoldText = true
        }
        if (reverse) {
            canvas?.rotate(180f, width / 2f, 170f)
            canvas?.drawText(resources.getString(R.string.serials_button), width / 2f + 85, height / 2f - 125, painter)
        } else {
            canvas?.drawText(resources.getString(R.string.films_button), width / 2f + 85, height / 2f + 125, painter)
        }

    }
}