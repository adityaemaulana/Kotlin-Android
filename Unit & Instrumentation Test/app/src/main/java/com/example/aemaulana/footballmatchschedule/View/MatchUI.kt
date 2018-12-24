package com.example.aemaulana.footballmatchschedule

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                textView {
                    id = R.id.tv_date
                    textSize = 13F
                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                    gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(width = matchParent, height = wrapContent)

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(8)
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 7F

                    textView {
                        id = R.id.tv_home
                        textSize = 18F
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams(width = dip(0), height = wrapContent, weight = 2F)

                    textView {
                        id = R.id.tv_home_score
                        textSize = 19F
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    textView("vs") {
                        textSize = 14F
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        gravity = Gravity.CENTER_VERTICAL
                    }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    textView {
                        id = R.id.tv_away_score
                        textSize = 19F
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        typeface = Typeface.DEFAULT_BOLD
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    textView {
                        id = R.id.tv_away
                        textSize = 18F
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams(width = dip(0), height = wrapContent, weight = 2F)
                }
            }
        }
    }

}