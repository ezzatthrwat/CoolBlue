package com.example.shopping.presentation.search.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.core.adapter.BaseListAdapter
import com.example.core.extension.loadImage
import com.example.core.model.asString
import com.example.shopping.databinding.AdapterProductBinding
import com.example.shopping.presentation.search.model.AvailabilityStateUi
import com.example.shopping.presentation.search.model.ProductUiModel

class ProductsAdapter(
    private val onProductItemClicked: (Long) -> Unit,
    private val onAddToCartClicked: (Long) -> Unit,
    private val onMailClicked: () -> Unit
) : BaseListAdapter<ProductUiModel, AdapterProductBinding>(diffCallback = ProductsDiffUtil) {

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
        binding.reviewsCountTextView.text = item.reviewCount.asString(context.resources)
        binding.productDescriptionTextView.text = item.uSPs
        binding.productPriceTextView.text = item.salesPriceIncVat

        bindInformationTitle(binding, item)
        bindRetailPriceTextView(binding, item)
        bindPromoType(binding, item, context)
        bindAvailabilityStatus(binding, item)

        binding.root.setOnClickListener { onProductItemClicked.invoke(item.productId) }
    }

    private fun bindInformationTitle(binding: AdapterProductBinding, item: ProductUiModel) {
        binding.choiceInfoTextView.isVisible = item.coolbluesChoiceInformationTitle.isNotEmpty()
        binding.choiceInfoTextView.text = item.coolbluesChoiceInformationTitle
    }

    private fun bindRetailPriceTextView(binding: AdapterProductBinding, item: ProductUiModel) {
        binding.retailerPriceGroup.isInvisible = item.listPriceIncVat.toString().isEmpty()
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
        item: ProductUiModel
    ) {

        with(item.availabilityState) {
            when (this) {
                AvailabilityStateUi.AVAILABLE -> {
                    binding.soldOutTextView.isVisible = false
                    binding.sendMailButton.isVisible = false
                    binding.deliveredTomorrowTextView.isVisible = true
                    binding.addToCartButton.isVisible = true
                    binding.addToCartButton.setOnClickListener { onAddToCartClicked.invoke(item.productId) }
                }
                AvailabilityStateUi.SOLD_OUT -> {
                    binding.soldOutTextView.isVisible = true
                    binding.sendMailButton.isVisible = true
                    binding.deliveredTomorrowTextView.isVisible = false
                    binding.addToCartButton.isVisible = false
                    binding.sendMailButton.setOnClickListener { onMailClicked.invoke() }
                }
            }
        }
    }
}
