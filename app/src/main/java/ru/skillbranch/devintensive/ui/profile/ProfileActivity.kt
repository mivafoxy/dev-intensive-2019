package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Bender

class ProfileActivity : AppCompatActivity(), View.OnClickListener
{
    private lateinit var benderImage: ImageView
    private lateinit var textTxt: TextView
    private lateinit var messageEt: EditText
    private lateinit var sendBtn: ImageView

    private lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("M_MainActivity", "onCreate")

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send
        sendBtn.setOnClickListener(this)

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        val retries = savedInstanceState?.getInt("RETRIES") ?: 0
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))
        benderObj.retries = retries

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()

        messageEt.setOnEditorActionListener(object : TextView.OnEditorActionListener
        {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean
            {
                if (p1 == EditorInfo.IME_ACTION_DONE && p0 != null)
                    changeBenderState()

                return true
            }

        })
    }

    @Override
    override fun onRestart()
    {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    override fun onStart()
    {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    override fun onResume()
    {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    override fun onPause()
    {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    override fun onStop()
    {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }

    override fun onClick(v: View?)
    {
        if(v?.id == R.id.iv_send)
        {
            changeBenderState()
        }
    }

    fun changeBenderState()
    {
        val (phrase, color) =
            benderObj.listenAnswer(
                messageEt.text.toString())

        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)

        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        outState?.putInt("RETRIES", benderObj.retries)

        Log.d("M_MainActivity", "onSaveInstanceState ${benderObj.status.name}")
    }
}
