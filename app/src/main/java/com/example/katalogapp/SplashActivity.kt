
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.katalogapp.MainActivity
import com.example.katalogapp.R
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        // Tampilkan teks atau elemen lainnya
        val textView:TextView = findViewById(R.id.title_splash)


        // Pindah ke MainActivity setelah beberapa detik
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000) // Menunggu 3 detik sebelum pindah ke MainActivity
    }
}

