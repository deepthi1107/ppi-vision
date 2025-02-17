package org.mifos.visionppi.ui.activities.base

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.mifos.visionppi.VisionPPI
import org.mifos.visionppi.injection.component.ActivityComponent
import org.mifos.visionppi.injection.component.DaggerActivityComponent
import org.mifos.visionppi.injection.module.ActivityModule
import org.mifos.visionppi.ui.views.BaseActivityCallback
import org.mifos.visionppi.utils.LanguageHelper

/**
 * @author HARSH-nith
 * @since 13/07/2022
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), BaseActivityCallback {
    /**
     * Used for dependency injection
     * @return [ActivityComponent] which is used for injection
     */
    var activityComponent: ActivityComponent? = null
        get() {
            if (field == null) {
                field = DaggerActivityComponent.builder()
                        .activityModule(ActivityModule(this))
                        .applicationComponent(VisionPPI[this].component())
                        .build()
            }
            return field
        }
        private set
    private var progress: ProgressDialog? = null

    /**
     * Displays a toast in current activity. The duration can of two types:
     *
     *  * SHORT
     *  * LONG
     *
     *
     * @param message Message that the toast must show.
     * @param toastType Duration for which the toast must be visible.
     */
    fun showToast(message: String, toastType: Int) {
        Toast.makeText(this@BaseActivity, message, toastType).show()
    }

    /**
     * Displays a ProgressDialog
     * @param message Message you want to display in Progress Dialog
     */
    override fun showProgressDialog(message: String?) {
        if (progress == null) {
            progress = ProgressDialog(this, ProgressDialog.STYLE_SPINNER)
            progress!!.setCancelable(false)
        }
        progress!!.setMessage(message)
        progress!!.show()
    }

    /**
     * Hides the progress dialog if it is currently being shown
     */
    override fun hideProgressDialog() {
        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
            progress = null
        }
    }

    /**
     * Used for setting title of Toolbar
     * @param title String you want to display as title
     */
    private fun setActionBarTitle(title: String?) {
        if (supportActionBar != null && getTitle() != null) {
            setTitle(title)
        }
    }

    /**
     * Calls `setActionBarTitle()` to set Toolbar title
     * @param title String you want to set as title
     */
    override fun setToolbarTitle(title: String?) {
        setActionBarTitle(title)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LanguageHelper.onAttach(base))
    }
}
