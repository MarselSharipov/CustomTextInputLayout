package ru.unluckybatman.material.textfield

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Created by Dinorah Tovar on 6/19/18
 * Password with
 */
class CustomTextInputLayout @JvmOverloads
/**
 * Constructor
 * @param context Context
 * @param attrs Attribute Set for view
 * @param defStyleAttr Int style from attr
 */
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextInputLayout(context, attrs, defStyleAttr) {

    private var bounds: Rect? = null
    private var recalculateMethod: Method? = null
    private var collapsingTextHelper1: Any? = null

    /**
     * Companion Object
     */
    companion object {
        const val COLLAPSING_HELPER = "collapsingTextHelper"
        const val COLLAPSED_BOUNDS = "collapsedBounds"
        const val RECALCULATE = "recalculate"
    }

    /**
     * Init constructors
     */
    init {
        init()
    }

    /**
     * On layout changes
     * @param changed Boolean
     * @param left Int
     * @param top Int
     * @param right Int
     * @param bottom Int
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        adjustBounds()
    }

    /**
     * Init function call the TextInputLayout class and the secondary internal class CollapsingTextHelper
     * @see TextInputLayout
     */
    private fun init() {
        try {
            //Search internal and private class over object called as variable
            val cthField = TextInputLayout::class.java.getDeclaredField(COLLAPSING_HELPER)
            cthField.isAccessible = true
            collapsingTextHelper1 = cthField.get(this)

            //Search in private class the other component to create a view
            val boundsField = collapsingTextHelper1?.javaClass?.getDeclaredField(COLLAPSED_BOUNDS)
            boundsField?.isAccessible = true
            bounds = boundsField?.get(collapsingTextHelper1) as Rect
            recalculateMethod = collapsingTextHelper1?.javaClass?.getDeclaredMethod(RECALCULATE)

        } catch (e: NoSuchFieldException) {
            collapsingTextHelper1 = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            collapsingTextHelper1 = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            collapsingTextHelper1 = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        }
    }

    /**
     * Adjust Bounds of the view for padding
     * and changes for the view
     */
    private fun adjustBounds() {
        if (collapsingTextHelper1 == null)
            return
        try {
            bounds?.left = editText?.left!! + editText?.paddingLeft!!
            recalculateMethod?.invoke(collapsingTextHelper1)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }
}