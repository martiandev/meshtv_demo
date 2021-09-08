package com.ph.bittelasia.meshtv.setup.expire

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ph.bittelasia.meshtv.R

class ExpirationActivity:AppCompatActivity() {

    var expirationFragment:ExpirationFragment ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_expire)
        expirationFragment = ExpirationFragment()
        supportFragmentManager.beginTransaction().add(R.id.fc_main,expirationFragment!!,"Expiry").commit()

    }
}