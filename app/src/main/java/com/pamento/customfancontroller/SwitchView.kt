package com.pamento.customfancontroller

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.pamento.customfancontroller.databinding.ComponentSwitchBinding

class SwitchView : MotionLayout {

    var onCheckListener: OnCheckListener? = null
    private lateinit var binding: ComponentSwitchBinding
    private var checked = false
    private var colorId = R.color.lightGrey


    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        inflate(getContext(), R.layout.component_switch, this)
        binding = ComponentSwitchBinding.bind(this)

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SwitchView, 0, 0)
        checked = typedArray.getBoolean(R.styleable.SwitchView_switchChecked, false)
        val backgroundColorId = typedArray.getResourceId(R.styleable.SwitchView_switchBackgroundColor, R.color.white)
        colorId = typedArray.getResourceId(R.styleable.SwitchView_switcherColor, R.color.lightGrey)
        binding.switchButton.backgroundTintList = ContextCompat.getColorStateList(context, backgroundColorId)
        binding.switchMl.setBackgroundColor(ContextCompat.getColor(context, backgroundColorId))
        binding.switchBackground.backgroundTintList = ContextCompat.getColorStateList(context, colorId)

        setCurrentState()
        binding.switchBackground.setOnClickListener {
            Log.i("SWITCHER", "init: setOnClickListener")
            switchClicked()
        }

        typedArray.recycle()
    }

    private fun setCurrentState() {
        Log.i("SWITCHER", "setCurrentState: checked::$checked")
        findViewById<MotionLayout>(R.id.switch_ml).apply {
            if (checked) {
               // this.setTransition(R.id.switch_off, R.id.switch_on)
                this.transitionToStart()
               /* setState(
                    R.id.switch_on,
                    ConstraintSet.MATCH_CONSTRAINT,
                    ConstraintSet.MATCH_CONSTRAINT
                )*/
            } else {
                this.transitionToEnd()
                /*this.setTransition(R.id.switch_on, R.id.switch_off)
                setState(
                    R.id.switch_off,
                    ConstraintSet.MATCH_CONSTRAINT,
                    ConstraintSet.MATCH_CONSTRAINT
                )*/
            }
            progress = 1.0f
        }
    }

    private fun switchClicked() {
        Log.i("SWITCHER", "switchClicked: checked::$checked")
//        if (!checked) {
//            toggleSwitch()
//            onCheckListener?.onSwitchToggled(checked, this)
//        }
        toggleSwitch()
    }

    private fun toggleSwitch() {
        Log.i("SWITCHER", "toggleSwitch: ")
        findViewById<MotionLayout>(R.id.switch_ml).apply {
            progress = 0.0f
            if (checked) {
                this.transitionToStart()
                //setTransition(R.id.switch_off, R.id.switch_on)
            } else {
                this.transitionToEnd()
                //setTransition(R.id.switch_on, R.id.switch_off)
            }
            transitionToEnd()
        }
    }

    fun setSwitchChecked() {
        checked = !checked
        Log.i("SWITCHER", "setSwitchChecked: checked::$checked")
        toggleSwitch()
    }

    interface OnCheckListener {
        fun onSwitchToggled(check: Boolean, switched: SwitchView)
    }
}