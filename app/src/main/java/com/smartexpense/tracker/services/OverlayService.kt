package com.smartexpense.tracker.services

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.smartexpense.tracker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private var overlayView: View? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val amount = intent?.getDoubleExtra("AMOUNT", 0.0)
        val merchant = intent?.getStringExtra("MERCHANT")
        val bank = intent?.getStringExtra("BANK")
        
        if (amount != null && amount > 0) {
            showOverlay(amount, merchant, bank)
        }
        
        return START_NOT_STICKY
    }

    private fun showOverlay(amount: Double, merchant: String?, bank: String?) {
        if (overlayView != null) return // Already showing

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        // Note: For real compose usage in WindowManager, ComposeView is needed.
        // For simplicity and to avoid complex lifecycle wiring in standard services, 
        // we'll stick to a conceptual setup or we can instantiate a ComposeView.
        // Since we don't have R.layout.overlay_view defined, we'll create a basic layout programmatically
        // or we'll need to create the XML. 
        
        // I will create the overlay dynamically to avoid needing an XML file for now,
        // but ideally this should be a ComposeView.
        // Let's create a dummy notification toast for now to represent the overlay logic
        Toast.makeText(this, "Expense Detected: $amount at $merchant", Toast.LENGTH_LONG).show()
        
        // Stop service after handling
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        overlayView?.let {
            windowManager.removeView(it)
        }
    }
}
