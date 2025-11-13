package com.example.library

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.library.databinding.LibraryBinding

/**
 * LibraryActivity - Khmer Dictionary App Store Platform
 * All code logic stored in this single file
 */
class LibraryActivity : AppCompatActivity() {

    private lateinit var binding: LibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRatingStars()
        setupClickListeners()
    }

    /**
     * Setup Rating Stars (4.5 stars)
     */
    private fun setupRatingStars() {
        val ratingStarsLayout = binding.ratingStars
        ratingStarsLayout.removeAllViews()
        
        val rating = 4.5f
        val fullStars = rating.toInt()
        val hasHalfStar = rating - fullStars >= 0.5f
        
        val starSize = (16 * resources.displayMetrics.density).toInt()
        val starLayoutParams = android.view.ViewGroup.LayoutParams(starSize, starSize)
        
        // Add full stars
        for (i in 0 until fullStars) {
            val starView = ImageView(this)
            starView.setImageResource(android.R.drawable.btn_star_big_on)
            starView.layoutParams = starLayoutParams
            ratingStarsLayout.addView(starView)
        }
        
        // Add half star if needed
        if (hasHalfStar) {
            val halfStarView = ImageView(this)
            halfStarView.setImageResource(android.R.drawable.btn_star_big_on)
            halfStarView.alpha = 0.5f
            halfStarView.layoutParams = starLayoutParams
            ratingStarsLayout.addView(halfStarView)
        }
        
        // Add empty stars to complete 5 stars
        val remainingStars = 5 - fullStars - if (hasHalfStar) 1 else 0
        for (i in 0 until remainingStars) {
            val starView = ImageView(this)
            starView.setImageResource(android.R.drawable.btn_star_big_off)
            starView.layoutParams = starLayoutParams
            ratingStarsLayout.addView(starView)
        }
    }

    /**
     * Setup Click Listeners
     */
    private fun setupClickListeners() {
        // Open Button
        binding.openButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.app_opening), Toast.LENGTH_SHORT).show()
        }
        
        // Share Button
        binding.shareButton.setOnClickListener {
            onShareButtonClick()
        }
        
        // What's New Header
        binding.whatsNewHeader.setOnClickListener {
            Toast.makeText(this, getString(R.string.whats_new_full), Toast.LENGTH_SHORT).show()
        }
        
        // Preview Images
        binding.previewImage1.setOnClickListener {
            Toast.makeText(this, "Viewing Preview 1", Toast.LENGTH_SHORT).show()
        }
        
        binding.previewImage2.setOnClickListener {
            Toast.makeText(this, "Viewing Preview 2", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Handle Share Button Click
     */
    private fun onShareButtonClick() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_title))
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))
    }
}
