package se.linerotech.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mywheaterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            if (binding.mainEditText.text.isEmpty() || binding.mainEditText.text.isBlank()) {
                Toast.makeText(this, ("Please enter a city"), Toast.LENGTH_LONG).show()
            }else {
                if (binding.mainEditText.text.toString().lowercase()matches(regex1)) {

                    }

                }
            }
        }

    }

    private val regex1 = Regex("panama")
    private val regex2 = Regex("gothenburg")
    private val regex3 = Regex("oslo")
    private val regex4 = Regex("london")




