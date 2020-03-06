package de.hornings.sftp

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.acivity_add_server.*
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.common.SecurityUtils
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.lang.Integer.parseInt
import java.security.Security


class AddServerActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acivity_add_server)

        // Hack to get rid of thread warning errors, see
        // https://stackoverflow.com/questions/25093546/android-os-networkonmainthreadexception-at-android-os-strictmodeandroidblockgua
        // TODO: might have to run ssh actions in an actual thread to solve it properly
        // Docs https://developer.android.com/training/basics/network-ops/connecting
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        SecurityUtils.registerSecurityProvider("org.bouncycastle.jce.provider.BouncyCastleProvider()")

        val ssh = SSHClient()
        // ssh.loadKnownHosts()

        saveServerInfo.setOnClickListener{
            val sshPort = port.text.toString()
            var sshPortInt = 22

            try {
                sshPortInt = parseInt(sshPort)
            } catch (e: NumberFormatException) {
                sshPortInt = 22
            }


            d("serverview", "Info: sftp://${userName.text}:${password.text}@${hostname.text}:${sshPortInt}")


            ssh.connect(hostname.text.toString(), sshPortInt)
            /*
            ssh.authPassword(userName.text.toString(), password.text.toString())

            var homeDir = "/home/${userName.text}/"
            val sftp = ssh.newSFTPClient()
            var fileList = sftp.ls(homeDir)
            for(resource in fileList) {
                d("serverview", "File: ${resource}")
            }
            */


        }
    }
}