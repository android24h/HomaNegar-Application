package com.example.myapplication.presentation.util

import saman.zamani.persiandate.PersianDate

object PersianDateUtil {

    fun today(): String {
        return formatDate(PersianDate())
    }

    private fun formatDate(date: PersianDate): String {

        val year = date.shYear
        val month = date.shMonth.toString().padStart(2, '0')
        val day = date.shDay.toString().padStart(2, '0')

        return "$year/$month/$day"
    }

    // -----------------------------
    // تاریخ 7 روز اخیر
    // -----------------------------

    fun last7Days(): List<String> {

        val dates = mutableListOf<String>()

        repeat(7) { index ->

            val millis =
                System.currentTimeMillis() -
                        (index * 24 * 60 * 60 * 1000L)

            val date = PersianDate(millis)

            dates.add(formatDate(date))
        }

        return dates
    }

    fun monthsOfYear(): List<Pair<String, String>> {

        val year = PersianDate().shYear

        return listOf(
            "فروردین $year" to "$year/01",
            "اردیبهشت $year" to "$year/02",
            "خرداد $year" to "$year/03",
            "تیر $year" to "$year/04",
            "مرداد $year" to "$year/05",
            "شهریور $year" to "$year/06",
            "مهر $year" to "$year/07",
            "آبان $year" to "$year/08",
            "آذر $year" to "$year/09",
            "دی $year" to "$year/10",
            "بهمن $year" to "$year/11",
            "اسفند $year" to "$year/12"
        )
    }

    // -----------------------------
    // سال‌ها
    // -----------------------------

    fun getYears(): List<Int> {

        val currentYear = PersianDate().shYear

        return (currentYear - 5..currentYear + 1).toList()
    }

    // -----------------------------
    // ماه‌ها
    // -----------------------------

    fun getMonths(year: Int): List<Pair<String, Int>> {

        return listOf(
            "فروردین" to 1,
            "اردیبهشت" to 2,
            "خرداد" to 3,
            "تیر" to 4,
            "مرداد" to 5,
            "شهریور" to 6,
            "مهر" to 7,
            "آبان" to 8,
            "آذر" to 9,
            "دی" to 10,
            "بهمن" to 11,
            "اسفند" to 12
        )
    }

    // -----------------------------
    // روزهای ماه
    // -----------------------------

    fun getDays(
        year: Int,
        month: Int
    ): List<Int> {

        val daysInMonth = when (month) {

            in 1..6 -> 31

            in 7..11 -> 30

            12 -> {

                if (isLeapYear(year)) {
                    30
                } else {
                    29
                }
            }

            else -> 30
        }

        return (1..daysInMonth).toList()
    }

    // -----------------------------
    // تشخیص سال کبیسه
    // -----------------------------

    private fun isLeapYear(year: Int): Boolean {

        val remainder = year % 33

        return remainder in listOf(
            1,
            5,
            9,
            13,
            17,
            22,
            26,
            30
        )
    }

    // -----------------------------
    // ساخت تاریخ
    // -----------------------------

    fun createDate(
        year: Int,
        month: Int,
        day: Int
    ): String {

        return "$year/" +
                month.toString().padStart(2, '0') +
                "/" +
                day.toString().padStart(2, '0')
    }

    // -----------------------------
    // نام ماه
    // -----------------------------

    fun getMonthName(month: Int): String {

        return when (month) {

            1 -> "فروردین"
            2 -> "اردیبهشت"
            3 -> "خرداد"
            4 -> "تیر"
            5 -> "مرداد"
            6 -> "شهریور"
            7 -> "مهر"
            8 -> "آبان"
            9 -> "آذر"
            10 -> "دی"
            11 -> "بهمن"
            12 -> "اسفند"

            else -> ""
        }
    }
}