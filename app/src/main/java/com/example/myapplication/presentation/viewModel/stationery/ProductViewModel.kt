package com.example.myapplication.presentation.viewModel.stationery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.stationery.ProductData
import com.example.myapplication.domain.useCase.stationery.GetAllProduct
import com.example.myapplication.domain.useCase.stationery.GetAvailableProducts
import com.example.myapplication.domain.useCase.stationery.GetProductWithId
import com.example.myapplication.domain.useCase.stationery.ProductDeleteUseCase
import com.example.myapplication.domain.useCase.stationery.ProductUpsertUseCase
import com.example.myapplication.domain.useCase.stationery.SearchProductByName
import com.example.myapplication.presentation.event.ProductEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val upsertProductUseCase: ProductUpsertUseCase,
    private val deleteProductUseCase: ProductDeleteUseCase,
    private val getAllProductUseCase: GetAllProduct,
    private val searchByNameUseCase: SearchProductByName,
    private val getProductWithIdUseCase: GetProductWithId,
    private val getAvailableProductsUseCase: GetAvailableProducts

) : ViewModel() {


    private val _isLoadings = MutableStateFlow(false)
    val isLoading = _isLoadings.asStateFlow()

    private val _productList = MutableStateFlow(listOf<ProductData>())
    val productList = _productList.asStateFlow()

    private val _selectedData = MutableStateFlow<ProductData?>(null)
    val selectedData = _selectedData.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)

    private val _event= Channel<ProductEvent>()
    val event=_event.receiveAsFlow()



    init {
        loadProduct()
    }

    fun changeStockAfterSaleEdit(
        productId: Int,
        oldQuantity: Int,
        newQuantity: Int
    ) {
        viewModelScope.launch {

            try {

                val product =
                    getProductWithIdUseCase(productId)

                if (product != null) {

                    val difference =
                        newQuantity - oldQuantity

                    val newStock =
                        product.stock - difference

                    if (newStock < 0) {
                        return@launch
                    }

                    val updatedProduct =
                        product.copy(
                            stock = newStock
                        )

                    upsertProductUseCase(
                        updatedProduct
                    )
                }

            } catch (e: Exception) {

                _errorMessage.value =
                    e.message ?: "خطا در تغییر موجودی"
            }
        }
    }

    fun clearSelectedProduct() {
        _selectedData.value = null
    }

    fun increaseStock(
        productId: Int,
        quantity: Int
    ) {

        viewModelScope.launch {

            try {

                val product =
                    getProductWithIdUseCase(
                        productId
                    )

                if (product != null) {

                    val updatedProduct =
                        product.copy(
                            stock =
                                product.stock + quantity
                        )

                    upsertProductUseCase(
                        updatedProduct
                    )
                }

            } catch (e: Exception) {

                _errorMessage.value =
                    e.message
                        ?: "خطا در بازگرداندن موجودی"
            }
        }
    }

    fun loadProduct() {
        Log.d("ProductVM", "loadProduct Start")
        _isLoadings.value = true

        viewModelScope.launch {
            try {
                getAllProductUseCase().collect { product ->
                    Log.d("ProductVM", "Products Count = ${product.size}")
                    _productList.value = product
                }

            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "خطا در دریافت اطلاعات"
            } finally {
                _isLoadings.value = false
            }
        }
    }


    fun upsertProduct(productData: ProductData){
        _isLoadings.value=true
        viewModelScope.launch {
            try {
                Log.d("ProductVM", "Saving Product...")
                upsertProductUseCase(productData)
                Log.d("ProductVM", "Saved")
              _event.send(
                  ProductEvent.Success(
                      message = "محصول با موفقیت اضافه شد"
                  )
              )

            }catch (e: Exception){
                Log.e("ProductVM","Error = ${e.message}",e)
                _errorMessage.value=e.message?:"خطا در دریافت اطلاعات"
            }finally {
                _isLoadings.value=false
            }
        }
    }

    fun deleteProduct(productData: ProductData){
        _isLoadings.value=true
        viewModelScope.launch {
            try {
                deleteProductUseCase(productData)
                _event.send(
                    ProductEvent.DeleteSuccess(
                        message = "کلمه شما با موفقیت حذف شد"
                    )
                )

            }catch (e: Exception){
                _errorMessage.value=e.message?:"خطا در فرآیند پاک کردن دیتا"
            }finally {
                _isLoadings.value=false
            }
        }
    }

    fun searchProduct(name: String){
        _isLoadings.value=true
        viewModelScope.launch {
            try {
                searchByNameUseCase(name).collect {products->
                    _productList.value=products

                }

            }catch (e: Exception){
                _errorMessage.value=e.message?:"جستجو با خطا مواجه شد"

            }finally {
                _isLoadings.value=false
            }
        }

    }

    fun productWithId(id: Int){
        _isLoadings.value=true
        viewModelScope.launch {
            try {
             val product= getProductWithIdUseCase(id)
                _selectedData.value=product

            }catch (e: Exception){
                _errorMessage.value=e.message?:"متن مورد نظر پیدا نشد"
            }finally {
                _isLoadings.value=false
            }
        }
    }

    fun availableProducts(){
        _isLoadings.value=true
        viewModelScope.launch {
            try{

                getAvailableProductsUseCase().collect {products->

                    _productList.value=products
                }

            }catch (e: Exception){
                _errorMessage.value=e.message?:"عبارت انتخابی فعال نیس"

            }finally {
                _isLoadings.value=false
            }
        }

    }




}