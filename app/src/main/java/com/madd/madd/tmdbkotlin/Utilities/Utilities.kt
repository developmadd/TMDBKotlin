package com.madd.madd.tmdbkotlin.Utilities

import android.animation.Animator
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.tabs.TabLayout
import java.text.Normalizer
import java.util.*

class Utilities {

    interface GetAnimationStatus {
        fun animationStatus(animationStatus: Boolean)
    }

    companion object {

        // Recursos para animacion de traslacion en Y
        fun animateTranslationY(v: View, distance: Int, getAnimationStatus: GetAnimationStatus?) {
            val viewPropertyAnimator = v.animate().translationY(getDP(v.context, distance).toFloat())
            if (getAnimationStatus != null) {
                viewPropertyAnimator.setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator) {
                        getAnimationStatus.animationStatus(true)
                    }

                    override fun onAnimationEnd(animator: Animator) {
                        getAnimationStatus.animationStatus(false)
                    }

                    override fun onAnimationCancel(animator: Animator) {
                        // Not required
                    }

                    override fun onAnimationRepeat(animator: Animator) {
                        // Not required
                    }
                })
            }
            viewPropertyAnimator.start()
        }

        private fun getDP(context: Context, dps: Int): Int {
            val scale = context.resources.displayMetrics.density
            return (dps * scale + 0.5f).toInt()
        }


        // Limpia strings para su comparación

        fun cleanString(string: String): String {
            val normalize = Normalizer.normalize(string.toLowerCase(), java.text.Normalizer.Form.NFD)
                .replace("[^\\p{ASCII}]".toRegex(), "")
            return normalize.trim { it <= ' ' }
        }


        // Administra el hide del keyboard para un control o view en especifico

        fun hideKeyboardFrom(view: View) {
            view.requestFocus()
            view.postDelayed({
                try {
                    val keyboard =
                        view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    keyboard.hideSoftInputFromWindow(view.windowToken, 0)
                } catch (ignored: Exception) {
                    // Ignored code block
                }
            }, 200)
        }


        // Administra el hide del keyboard al cambiar de item en tabs

        fun hideKeyboardFromTab(tabLayout: TabLayout) {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    Utilities.hideKeyboardFrom(tabLayout)
                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }

}