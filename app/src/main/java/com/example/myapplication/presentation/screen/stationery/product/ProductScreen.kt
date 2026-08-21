package com.example.myapplication.presentation.screen.stationery.product

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.domain.model.stationery.ProductData
import com.example.myapplication.presentation.components.ProductTextField
import com.example.myapplication.presentation.util.toPersianNumber
import com.example.myapplication.presentation.viewModel.stationery.ProductViewModel

@Composable
fun ProductScreen(
    myViewModel: ProductViewModel= hiltViewModel()
) {

    val productList by myViewModel.productList.collectAsState()
    val selectedProduct by myViewModel.selectedData.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        ProductHeader()
        ProductForm(myViewModel=myViewModel,selectedProduct=selectedProduct)
        //   RegisterButton()
        ProductList(productList=productList, modifier = Modifier.weight(1f),myViewModel=myViewModel)

    }


}


@Composable
fun ProductHeader() {

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),

                ) {

                Text(
                    stringResource(R.string.TitleProduct),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.Default.Inventory2,
                    contentDescription = null,
                    modifier = Modifier
                        .align(
                            Alignment.CenterEnd
                        )
                        .padding(end = 16.dp)
                )

            }

        }

    }


}

@Composable
fun ProductForm(
    myViewModel: ProductViewModel,
    selectedProduct: ProductData?
) {

    val context = LocalContext.current

    var txtNameProduct by remember { mutableStateOf("") }
    var txtPurchasePrice by remember { mutableStateOf("") }
    var txtSalePrice by remember { mutableStateOf("") }
    var txtStock by remember { mutableStateOf("") }

    var editingId by remember { mutableStateOf<Int?>(null) }
    var isEditing by remember { mutableStateOf(false) }


    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            elevation = CardDefaults.cardElevation(4.dp),

            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "اطلاعات محصول",

                    modifier =
                        Modifier.align(Alignment.Start),

                    fontSize = 18.sp,

                    fontWeight =
                        FontWeight.Bold
                )


                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                // نام کالا
                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

            /*        Text(
                        text = "نام کالا",

                        modifier =
                            Modifier.weight(0.10f),

                        maxLines = 1
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )*/

                    ProductTextField(
                        value = txtNameProduct,

                        onValueChange = {
                            txtNameProduct = it
                        },

                        placeHolder =
                            "نام کالا را وارد کن",

                        keyboardType =
                            KeyboardType.Text,

                        modifier =
                            Modifier.weight(1f)
                    )
                }


                // قیمت خرید
                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

             /*       Text(
                        text = "قیمت خرید",

                        modifier =
                            Modifier.weight(0.20f),

                        maxLines = 1
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )*/

                    ProductTextField(
                        value =
                            txtPurchasePrice,

                        onValueChange = {
                            txtPurchasePrice = it
                        },

                        placeHolder =
                            "قیمت خرید کالا را مشخص کن",

                        keyboardType =
                            KeyboardType.Number,

                        modifier =
                            Modifier.weight(1f)
                    )
                }


                // قیمت فروش
                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

             /*       Text(
                        text = "قیمت فروش",

                        modifier =
                            Modifier.weight(0.20f)
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )*/

                    ProductTextField(
                        value =
                            txtSalePrice,

                        onValueChange = {
                            txtSalePrice = it
                        },

                        placeHolder =
                            "قیمت فروش کالا را مشخص کن",

                        keyboardType =
                            KeyboardType.Number,

                        modifier =
                            Modifier.weight(1f)
                    )
                }


                // پر کردن اطلاعات هنگام ویرایش
                LaunchedEffect(selectedProduct) {

                    selectedProduct?.let {

                        txtNameProduct =
                            it.productName

                        txtPurchasePrice =
                            it.purchasePrice.toString()

                        txtSalePrice =
                            it.salesPrice.toString()

                        txtStock =
                            it.stock.toString()

                        editingId =
                            it.id

                        isEditing =
                            true
                    }
                }


                // موجودی کالا
                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

               /*     Text(
                        text = "موجودی کالا",

                        modifier =
                            Modifier.weight(0.20f)
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )*/

                    ProductTextField(
                        value =
                            txtStock,

                        onValueChange = {
                            txtStock = it
                        },

                        placeHolder =
                            "موجودی کالا را مشخص کن",

                        keyboardType =
                            KeyboardType.Number,

                        modifier =
                            Modifier.weight(1f)
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                fun createProduct(): ProductData {

                    return ProductData(

                        id =
                            editingId ?: 0,

                        productName =
                            txtNameProduct,

                        purchasePrice =
                            txtPurchasePrice.toInt(),

                        salesPrice =
                            txtSalePrice.toInt(),

                        stock =
                            txtStock.toInt()
                    )
                }


                RegisterButton(

                    text =
                        if (isEditing)
                            "ویرایش محصول"
                        else
                            "ثبت محصول",

                    onClick = {

                        if (txtNameProduct.isBlank()) {

                            Toast.makeText(
                                context,
                                "نام محصول نمی شود خالی باشد",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@RegisterButton
                        }


                        if (txtPurchasePrice.isBlank()) {

                            Toast.makeText(
                                context,
                                "قیمت خرید نمی تواند خالی باشد",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@RegisterButton
                        }


                        if (txtSalePrice.isBlank()) {

                            Toast.makeText(
                                context,
                                "قیمت فروش نمی تواند خالی باشد",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@RegisterButton
                        }


                        if (txtStock.isBlank()) {

                            Toast.makeText(
                                context,
                                "موجودی نمی تواند خالی باشد",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@RegisterButton
                        }


                        val product =
                            createProduct()

                        myViewModel.upsertProduct(
                            product
                        )

                        myViewModel.clearSelectedProduct()


                        txtNameProduct = ""
                        txtPurchasePrice = ""
                        txtSalePrice = ""
                        txtStock = ""

                        editingId = null
                        isEditing = false
                    }
                )
            }
        }
    }
}


@Composable
fun RegisterButton(
    text: String,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text
        )

    }

}


@Composable
fun ProductList(
    productList: List<ProductData>,
    modifier: Modifier = Modifier,
    myViewModel: ProductViewModel
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(12.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "لیست محصولات",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${productList.size.toPersianNumber()} محصول",
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 4.dp
                    ),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )




            }

        }


        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            productList.forEach { product ->

                ProductItem(
                    product = product,

                    onEditClick = {
                        myViewModel.productWithId(it.id)
                    },

                    onDeleteClick = {
                        myViewModel.deleteProduct(it)
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )
        }

    }

}

@Composable
fun ProductItem(
    product: ProductData,
    onEditClick: (ProductData) -> Unit = {},
    onDeleteClick: (ProductData) -> Unit = {}
) {

    val productIcon = Icons.Default.Inventory2

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            shape = RoundedCornerShape(14.dp),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {

            Column(
                modifier = Modifier.padding(12.dp)
            ) {


                // عنوان محصول + موجودی

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = productIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = product.productName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    ) {

                        Text(
                            text = "موجودی ${product.stock.toPersianNumber()}",
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 4.dp
                            ),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )

                    }

                }


                Spacer(modifier = Modifier.height(12.dp))


                // قیمت خرید و فروش + آیکون ها

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    // قیمت ها

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Text(
                            text = "خرید: ${product.purchasePrice.toPersianNumber()}",
                            fontSize = 13.sp
                        )


                        Text(
                            text = "فروش: ${product.salesPrice.toPersianNumber()}",
                            fontSize = 13.sp
                        )

                    }



                    // دکمه ها

                    Row {

                        IconButton(
                            onClick ={
                                onEditClick(product)
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "ویرایش",
                                tint = Color(0xFF1976D2)
                            )

                        }


                        IconButton(
                            onClick = {
                                onDeleteClick(product)
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "حذف",
                                //  tint = Color.Red
                            )

                        }

                    }

                }

            }

        }

    }

}