package com.example.shopping.presentation.search.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.core.adapter.BaseListAdapter
import com.example.core.extension.loadImage
import com.example.shopping.databinding.AdapterProductBinding
import com.example.shopping.presentation.search.model.ProductUiModel

class ProductsAdapter :
    BaseListAdapter<ProductUiModel, AdapterProductBinding>(diffCallback = ProductsDiffUtil) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): AdapterProductBinding {
        return AdapterProductBinding.inflate(inflater, parent, false)
    }

    override fun onBindItem(binding: AdapterProductBinding, item: ProductUiModel, position: Int) {
        val context = binding.root.context

        binding.productImageView.loadImage(item.productImage)

        binding.productNameTextView.text = item.productName
        binding.productRatingBar.rating = item.reviewAverage
        binding.reviewsCountTextView.text = item.reviewCount
        binding.productDescriptionTextView.text = item.uSPs
        binding.productPriceTextView.text = item.salesPriceIncVat

        bindInformationTitle(binding, item)
        bindRetailPriceTextView(binding, item)
        bindPromoType(binding, item, context)
        bindAvailabilityStatus(binding, item, context)
    }

    private fun bindInformationTitle(binding: AdapterProductBinding, item: ProductUiModel) {
        binding.choiceInfoTextView.isVisible = item.coolbluesChoiceInformationTitle.isNotEmpty()
        binding.choiceInfoTextView.text = item.coolbluesChoiceInformationTitle
    }

    private fun bindRetailPriceTextView(binding: AdapterProductBinding, item: ProductUiModel) {
        binding.retailPriceTextView.isInvisible = item.listPriceIncVat.toString().isNotEmpty()
        binding.retailPriceTextView.text = item.listPriceIncVat
    }

    private fun bindPromoType(
        binding: AdapterProductBinding,
        item: ProductUiModel,
        context: Context
    ) {
        item.promoIconType?.let {
            binding.promotionTextView.isVisible = true
            binding.promotionTextView.text = it.promoText
            binding.promotionTextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    it.promoTextColor
                )
            )
            binding.promotionTextView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    it.promoBackGroundColor
                )
            )
        } ?: run { binding.promotionTextView.isVisible = false }
    }

    private fun bindAvailabilityStatus(
        binding: AdapterProductBinding,
        item: ProductUiModel,
        context: Context
    ) {

        with(item.availabilityState) {
            when (this) {
                com.example.shopping.presentation.search.model.AvailabilityStateUi.AVAILABLE -> {
                    binding.soldOutTextView.isVisible = false
                    binding.deliveredTomorrowTextView.isVisible = true
                }
                com.example.shopping.presentation.search.model.AvailabilityStateUi.SOLD_OUT -> {
                    binding.soldOutTextView.isVisible = true
                    binding.deliveredTomorrowTextView.isVisible = false
                    binding.addToCartButton.backgroundTintList =
                        android.content.res.ColorStateList.valueOf(
                            ContextCompat.getColor(
                                context,
                                com.example.shopping.R.color.brand_blue
                            )
                        )
                    binding.addToCartButton.icon = ContextCompat.getDrawable(
                        context,
                        com.example.shopping.R.drawable.ic_mail
                    )
                }
            }
        }
    }
}
