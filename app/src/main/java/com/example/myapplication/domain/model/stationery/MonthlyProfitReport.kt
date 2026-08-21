package com.example.myapplication.domain.model.stationery

data class MonthlyProfitReport(
    val totalSalesAmount: Int,
    val totalPurchaseAmount: Int,
    val totalProfit: Int,
    val totalSaleCount: Int,
    val totalQuantity: Int
)
