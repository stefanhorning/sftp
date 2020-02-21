package de.hornings.sftp

import android.os.Bundle
import android.util.Log.d
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.acivity_add_server.*

class AddServerActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acivity_add_server)

        saveServerInfo.setOnClickListener{
            d("serverview", "Info: ${protocolDropdown.selectedItem.toString()} // ${hostname.text}:${port.text}")
        }
    }
}