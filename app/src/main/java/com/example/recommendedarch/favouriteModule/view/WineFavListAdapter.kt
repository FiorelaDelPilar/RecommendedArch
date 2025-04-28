package com.example.recommendedarch.favouriteModule.view

import androidx.recyclerview.widget.RecyclerView
import com.example.recommendedarch.BR
import com.example.recommendedarch.common.utils.OnClickListener
import com.example.recommendedarch.homeModule.view.WineDiff
import com.example.recommendedarch.homeModule.view.WineListAdapter

/****
 * Project: Wines
 * From: com.cursosant.wines
 * Created by Alain Nicolás Tello on 06/02/24 at 20:23
 * All rights reserved 2024.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class WineFavListAdapter(listener: OnClickListener, diff: WineDiff) :
    WineListAdapter(listener, diff) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as ViewHolder).binding?.setVariable(BR.isFavModule, true)
    }
}