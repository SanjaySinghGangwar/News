package com.trei.news.Ui.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.trei.news.R
import com.trei.news.Ui.Fragment.Setting
import com.trei.news.databinding.MainBinding

class Main : AppCompatActivity(), View.OnClickListener, Setting.settingListener {

    private lateinit var bind: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = MainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        initAllComponents()

    }

    private fun initAllComponents() {
        bind.setting.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.setting -> {
                val setting = Setting(this)
                setting.showNow(supportFragmentManager, "Setting")
            }
        }
    }

    override fun refreshNews() {
        intent= Intent(this,Main::class.java)
        startActivity(intent)
        finish()
    }
}