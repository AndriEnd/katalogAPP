
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.example.katalogapp.MainActivity
import com.example.katalogapp.R
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        installSplashScreen()


        // Ambil referensi ke logo
        val logo = findViewById<ImageView>(R.id.logo_splash)

        // Buat animasi (misalnya, fade-in dan scale-up)
        val fadeAnimator = ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f).apply {
            duration = 1000 // Durasi 1 detik
            interpolator = AccelerateDecelerateInterpolator()
        }

        val scaleXAnimator = ObjectAnimator.ofFloat(logo, "scaleX", 0.5f, 1f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }

        val scaleYAnimator = ObjectAnimator.ofFloat(logo, "scaleY", 0.5f, 1f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }

        // Mulai animasi
        fadeAnimator.start()
        scaleXAnimator.start()
        scaleYAnimator.start()

        // Pindah ke MainActivity setelah animasi selesai
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

