package org.mifos.visionppi.ui.views.base

/**
 * @author HARSH-nith
 * @since 13/07/2022
 */
interface MVPView {
    /**
     * Should be called when a time taking process starts and we want the user
     * to wait for the process to finish. The UI should gracefully display some
     * sort of progress bar or animation so that the user knows that the app is
     * doing some work and has not stalled.
     *
     * For example: a network request to the API is made for authenticating
     * the user.
     */
    fun showProgress()

    /**
     * Should be called when a time taking process ends and we have some result
     * for the user.
     */
    fun hideProgress()
}
