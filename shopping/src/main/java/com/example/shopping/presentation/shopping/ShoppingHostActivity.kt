package com.example.shopping.presentation.shopping

import android.view.LayoutInflater
import com.example.core.base.view.BaseActivity
import com.example.core.navigation.StartDestination
import com.example.shopping.R
import com.example.shopping.databinding.ShoppingHostActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingHostActivity : BaseActivity<ShoppingHostActivityBinding>() {

    override val activityTheme: Int = R.style.Theme_CoolBlueTask_shopping

    override val graph: Int = R.navigation.shopping_nav_graph

    override val navHostId: Int = R.id.navHost

    override fun startDestination(): StartDestination =
        StartDestination(R.id.searchFragment, null)

    override fun onCreateBinding(inflater: LayoutInflater): ShoppingHostActivityBinding {
        return ShoppingHostActivityBinding.inflate(inflater)
    }
}
